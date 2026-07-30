from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
from typing import List, Optional
from model import SkillMatcherModel
import uvicorn

app = FastAPI(
    title="SkillSwap AI API",
    description="AI-powered skill matching for the SkillSwap peer-to-peer platform",
    version="1.0.0"
)

# Allow CORS for Android HTTP requests
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Initialize the AI model
matcher = SkillMatcherModel()

# ── Pydantic Models ────────────────────────────────────────────────────────────

class CandidateProfile(BaseModel):
    uid: str
    name: str
    teach_skills: List[str]
    learning_skills: List[str]
    experience: str
    rating: float
    availability: List[str]
    college: str
    department: str

class MatchRequest(BaseModel):
    teach_skills: List[str]
    learning_skills: List[str]
    experience: str
    rating: float
    availability: List[str]
    candidates: List[CandidateProfile]

class RecommendedStudent(BaseModel):
    uid: str
    name: str
    college: str
    department: str
    teach_skills: List[str]
    learning_skills: List[str]
    experience: str
    rating: float
    availability: List[str]

class MatchResponse(BaseModel):
    recommended_student: RecommendedStudent
    match_percentage: float
    compatibility_score: float
    reason: List[str]


# ── Endpoints ──────────────────────────────────────────────────────────────────

@app.get("/health")
async def health_check():
    return {"status": "ok", "service": "SkillSwap AI API", "version": "1.0.0"}

@app.post("/match", response_model=MatchResponse)
async def find_skill_match(request: MatchRequest):
    """
    Find the best skill exchange match for a student using Random Forest AI.
    
    The model evaluates:
    - Skill complementarity (teach/learn overlap)
    - Experience compatibility
    - Rating score
    - Availability overlap
    """
    if not request.candidates:
        raise HTTPException(status_code=400, detail="No candidates provided")
    
    result = matcher.predict(request)
    
    if result is None:
        raise HTTPException(status_code=404, detail="No suitable match found")
    
    return result


if __name__ == "__main__":
    uvicorn.run("main:app", host="0.0.0.0", port=5000, reload=True)
