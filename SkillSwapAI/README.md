# SkillSwap AI 🔄

> A production-ready peer-to-peer student skill exchange platform powered by AI matching.

---

## 📱 Features

| Module | Description |
|---|---|
| 🔐 Authentication | Sign Up, Login, Forgot Password via Firebase Auth |
| 👤 Student Profile | Name, college, dept, year, bio, profile picture, skills |
| 🏠 Dashboard | Welcome banner, AI recommendations, popular skills, credits |
| 📚 Skill Management | Add/edit/delete teaching and learning skills |
| 🔍 Search | Search by skill, college, department, or rating |
| 🤖 AI Matching | Python Random Forest API → best match recommendation |
| 📨 Exchange Requests | Send/Accept/Reject/Cancel with status tracking |
| 📅 Sessions | Teacher/learner, date, duration, notes, mark complete |
| ⭐ Ratings | 5-star rating + feedback, updates user's overall score |
| 💎 Credits | Earn by teaching (+3), spend to learn (-1), balance tracking |
| 🔔 Notifications | New request, accepted, rating reminder, credit events |

---

## 🏗️ Architecture

```
Android App (Kotlin + Jetpack Compose)
├── MVVM + Repository Pattern
├── Hilt Dependency Injection
├── Firebase Auth → User authentication
├── Cloud Firestore → Real-time database
├── Firebase Storage → Profile pictures
├── Firebase Cloud Messaging → Push notifications
└── Python FastAPI (AI API) → Random Forest match recommendations
```

---

## 🚀 Setup Guide

### Step 1: Firebase Project

1. Go to [Firebase Console](https://console.firebase.google.com)
2. Create a new project called **SkillSwapAI**
3. Add an Android app with package name `com.skillswap.ai`
4. Download `google-services.json`
5. **Replace** `app/google-services.json` with your real file

#### Enable Firebase Services:
- Authentication → Email/Password
- Cloud Firestore → Start in test mode
- Firebase Storage → Start in test mode
- Cloud Messaging → No extra setup needed

---

### Step 2: Firestore Collections

Create these collections in Firestore (auto-created on first write):

| Collection | Description |
|---|---|
| `Users` | Student profiles |
| `ExchangeRequests` | Skill exchange requests |
| `Sessions` | Learning sessions |
| `Ratings` | Session ratings |
| `Notifications` | In-app notifications |
| `SkillCredits` | Credit transaction history |

#### Firestore Security Rules (Firestore Console → Rules):

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {

    // Users: read all, write own
    match /Users/{userId} {
      allow read: if request.auth != null;
      allow write: if request.auth != null && request.auth.uid == userId;
    }

    // Exchange Requests
    match /ExchangeRequests/{requestId} {
      allow read: if request.auth != null &&
        (request.auth.uid == resource.data.senderId ||
         request.auth.uid == resource.data.receiverId);
      allow create: if request.auth != null;
      allow update: if request.auth != null &&
        (request.auth.uid == resource.data.senderId ||
         request.auth.uid == resource.data.receiverId);
    }

    // Sessions
    match /Sessions/{sessionId} {
      allow read: if request.auth != null &&
        (request.auth.uid == resource.data.teacherId ||
         request.auth.uid == resource.data.learnerId);
      allow write: if request.auth != null;
    }

    // Ratings - read all authenticated, write own
    match /Ratings/{ratingId} {
      allow read: if request.auth != null;
      allow create: if request.auth != null && request.auth.uid == request.resource.data.raterId;
    }

    // Notifications - own only
    match /Notifications/{notifId} {
      allow read, write: if request.auth != null && request.auth.uid == resource.data.userId;
      allow create: if request.auth != null;
    }

    // Credits - own only
    match /SkillCredits/{creditId} {
      allow read: if request.auth != null && request.auth.uid == resource.data.userId;
      allow create: if request.auth != null;
    }
  }
}
```

---

### Step 3: Poppins Font

1. Download from [Google Fonts - Poppins](https://fonts.google.com/specimen/Poppins)
2. Download variants: Regular (400), Medium (500), SemiBold (600), Bold (700)
3. Create folder: `app/src/main/res/font/`
4. Add files:
   - `poppins_regular.ttf`
   - `poppins_medium.ttf`
   - `poppins_semibold.ttf`
   - `poppins_bold.ttf`

---

### Step 4: Python AI API

```bash
cd ai_api
pip install -r requirements.txt
python main.py
```

API runs at `http://localhost:5000`

- Android Emulator connects via `http://10.0.2.2:5000` ✅
- For production: deploy to a server and update `AI_API_BASE_URL` in `app/build.gradle`

**Test the API:**
```bash
curl http://localhost:5000/health
```

**Test the match endpoint:**
```bash
curl -X POST http://localhost:5000/match \
  -H "Content-Type: application/json" \
  -d '{
    "teach_skills": ["Python", "Machine Learning"],
    "learning_skills": ["Photoshop", "UI Design"],
    "experience": "Intermediate",
    "rating": 4.2,
    "availability": ["Mon", "Wed", "Fri"],
    "candidates": [
      {
        "uid": "abc123",
        "name": "Priya Sharma",
        "teach_skills": ["Photoshop", "Figma"],
        "learning_skills": ["Python", "Data Science"],
        "experience": "Advanced",
        "rating": 4.8,
        "availability": ["Mon", "Tue", "Wed"],
        "college": "IIT Delhi",
        "department": "Design"
      }
    ]
  }'
```

---

### Step 5: Open in Android Studio

1. Open **Android Studio** → File → Open → select `SkillSwapAI/`
2. Wait for Gradle sync
3. Add `google-services.json` (Step 1)
4. Add Poppins fonts (Step 3)
5. Start the Python AI API (Step 4)
6. Run on emulator (API 24+)

---

## 🎨 Design System

| Element | Value |
|---|---|
| Primary | `#1565C0` (Blue 700) |
| Secondary | `#6B30F5` (Purple) |
| Font | Poppins |
| Architecture | MVVM + Repository |
| Min SDK | 24 (Android 7.0) |
| Target SDK | 34 (Android 14) |
| Compose BOM | 2024.02.00 |

---

## 📁 Project Structure

```
SkillSwapAI/
├── app/src/main/java/com/skillswap/ai/
│   ├── data/
│   │   ├── model/          # Kotlin data classes
│   │   ├── remote/         # Retrofit AI API service
│   │   └── repository/     # Firebase + AI repositories
│   ├── di/                 # Hilt modules
│   ├── service/            # FCM service
│   ├── ui/
│   │   ├── auth/           # Login, Signup, ForgotPassword
│   │   ├── components/     # Reusable Compose components
│   │   ├── credits/        # Skill credits screen
│   │   ├── dashboard/      # Home screen
│   │   ├── matching/       # AI matching screen
│   │   ├── navigation/     # NavGraph + BottomNavBar
│   │   ├── notifications/  # Notifications screen
│   │   ├── profile/        # Profile screen
│   │   ├── ratings/        # Rating screen
│   │   ├── requests/       # Exchange requests
│   │   ├── search/         # Search screen
│   │   ├── sessions/       # Learning sessions
│   │   ├── skills/         # Skill management
│   │   └── theme/          # Material 3 theme
│   ├── MainActivity.kt
│   └── SkillSwapApp.kt
└── ai_api/
    ├── main.py             # FastAPI server
    ├── model.py            # Random Forest matcher
    └── requirements.txt
```

---

## 🔄 Replacing the Mock AI Model

In `ai_api/model.py`, replace the scoring logic with your trained Random Forest:

```python
import joblib
import numpy as np

class SkillMatcherModel:
    def __init__(self):
        self.model = joblib.load("random_forest_model.pkl")
        self.label_encoder = joblib.load("label_encoder.pkl")
    
    def predict(self, request):
        # Extract features for each candidate
        # ...
        X = np.array([[feature_vector]])
        probability = self.model.predict_proba(X)[0][1]
        # Return top candidate
```

---

## 📄 License

MIT License — free to use for educational projects.
