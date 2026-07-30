"""
SkillSwap AI — Skill Matching Model
====================================
Uses a Random Forest classifier to predict the best skill exchange partner.

Features used:
- Skill overlap score (teach/learn complementarity)
- Experience level compatibility
- Rating score
- Availability overlap ratio

Replace the mock implementation below with your trained Random Forest model.
"""

import random
from typing import Optional, List


# Experience level weights for compatibility
EXPERIENCE_LEVELS = {
    "Beginner": 1,
    "Intermediate": 2,
    "Advanced": 3
}


class SkillMatcherModel:
    """
    Random Forest-based skill matcher.

    In production:
        self.model = joblib.load("random_forest_model.pkl")
    
    For development/testing, a rule-based scoring algorithm is used
    that mimics what the Random Forest would output.
    """

    def __init__(self):
        # In production, load the trained model:
        # import joblib
        # self.model = joblib.load("random_forest_model.pkl")
        self.model = None  # Mock mode
        print("✅ SkillSwap AI Model initialized (mock mode)")

    def _compute_features(self, requester: dict, candidate: dict) -> dict:
        """Extract feature vector for a requester-candidate pair."""
        
        # Skill complementarity: can candidate teach what requester wants to learn?
        r_learn = set(requester["learning_skills"])
        c_teach = set(candidate["teach_skills"])
        teach_match = len(r_learn & c_teach) / max(len(r_learn), 1)

        # Can requester teach what candidate wants to learn?
        r_teach = set(requester["teach_skills"])
        c_learn = set(candidate["learning_skills"])
        learn_match = len(c_learn & r_teach) / max(len(c_learn), 1)

        # Bidirectional compatibility score
        skill_score = (teach_match + learn_match) / 2

        # Experience compatibility (closer = better)
        r_exp = EXPERIENCE_LEVELS.get(requester["experience"], 1)
        c_exp = EXPERIENCE_LEVELS.get(candidate["experience"], 1)
        exp_compat = 1.0 - abs(r_exp - c_exp) / 3.0

        # Rating (higher is better)
        rating_score = candidate["rating"] / 5.0

        # Availability overlap
        r_avail = set(requester["availability"])
        c_avail = set(candidate["availability"])
        avail_overlap = len(r_avail & c_avail) / max(len(r_avail | c_avail), 1)

        return {
            "skill_score": skill_score,
            "exp_compat": exp_compat,
            "rating_score": rating_score,
            "avail_overlap": avail_overlap,
            "teach_match": teach_match,
            "learn_match": learn_match
        }

    def _score_candidate(self, features: dict) -> float:
        """
        Compute a composite match score.
        
        In production, replace this with:
            X = np.array([[...features...]])
            return self.model.predict_proba(X)[0][1]
        """
        # Weighted scoring (mimics Random Forest feature importance)
        weights = {
            "skill_score":   0.40,
            "rating_score":  0.25,
            "exp_compat":    0.20,
            "avail_overlap": 0.15
        }
        score = (
            features["skill_score"]   * weights["skill_score"]   +
            features["rating_score"]  * weights["rating_score"]  +
            features["exp_compat"]    * weights["exp_compat"]     +
            features["avail_overlap"] * weights["avail_overlap"]
        )
        return round(score, 4)

    def _generate_reasons(self, features: dict, candidate: dict, requester: dict) -> List[str]:
        """Generate human-readable reasons for the recommendation."""
        reasons = []

        r_learn = set(requester["learning_skills"])
        c_teach = set(candidate["teach_skills"])
        overlap = list(r_learn & c_teach)
        if overlap:
            reasons.append(f"Can teach: {', '.join(overlap[:2])}")

        r_teach = set(requester["teach_skills"])
        c_learn = set(candidate["learning_skills"])
        reverse_overlap = list(c_learn & r_teach)
        if reverse_overlap:
            reasons.append(f"Wants to learn: {', '.join(reverse_overlap[:2])}")

        if candidate["rating"] >= 4.0:
            reasons.append(f"High rating ({candidate['rating']:.1f} ⭐)")

        if features["avail_overlap"] > 0.3:
            reasons.append("Availability overlaps well")

        exp_match = abs(
            EXPERIENCE_LEVELS.get(requester["experience"], 1) -
            EXPERIENCE_LEVELS.get(candidate["experience"], 1)
        ) <= 1
        if exp_match:
            reasons.append(f"Compatible experience level ({candidate['experience']})")

        if not reasons:
            reasons.append("Good overall compatibility")

        return reasons

    def predict(self, request) -> Optional[dict]:
        """Find the best match candidate for the requester."""
        
        requester = {
            "teach_skills":     request.teach_skills,
            "learning_skills":  request.learning_skills,
            "experience":       request.experience,
            "rating":           request.rating,
            "availability":     request.availability
        }

        scored = []
        for candidate in request.candidates:
            candidate_dict = {
                "uid":             candidate.uid,
                "name":            candidate.name,
                "teach_skills":    candidate.teach_skills,
                "learning_skills": candidate.learning_skills,
                "experience":      candidate.experience,
                "rating":          candidate.rating,
                "availability":    candidate.availability,
                "college":         candidate.college,
                "department":      candidate.department
            }
            features = self._compute_features(requester, candidate_dict)
            score = self._score_candidate(features)
            scored.append((score, features, candidate_dict))

        if not scored:
            return None

        # Sort by score descending
        scored.sort(key=lambda x: x[0], reverse=True)
        best_score, best_features, best_candidate = scored[0]

        match_percentage = round(best_score * 100, 1)
        compatibility_score = round(
            (best_features["skill_score"] * 0.5 + best_features["exp_compat"] * 0.5) * 100,
            1
        )

        return {
            "recommended_student": {
                "uid":             best_candidate["uid"],
                "name":            best_candidate["name"],
                "college":         best_candidate["college"],
                "department":      best_candidate["department"],
                "teach_skills":    best_candidate["teach_skills"],
                "learning_skills": best_candidate["learning_skills"],
                "experience":      best_candidate["experience"],
                "rating":          best_candidate["rating"],
                "availability":    best_candidate["availability"]
            },
            "match_percentage":   match_percentage,
            "compatibility_score": compatibility_score,
            "reason": self._generate_reasons(best_features, best_candidate, requester)
        }
