"""
Firestore Seed Data Script
==========================
Populates Firestore with sample student data for testing.

Usage:
    pip install firebase-admin
    python seed_firestore.py

You need a serviceAccountKey.json from Firebase Console:
    Project Settings → Service Accounts → Generate new private key
"""

import firebase_admin
from firebase_admin import credentials, firestore
from datetime import datetime

# Initialize Firebase Admin
cred = credentials.Certificate("serviceAccountKey.json")
firebase_admin.initialize_app(cred)
db = firestore.client()

SAMPLE_USERS = [
    {
        "uid": "sample_user_1",
        "email": "priya@example.com",
        "name": "Priya Sharma",
        "college": "IIT Delhi",
        "department": "Computer Science",
        "year": "3rd Year",
        "bio": "Passionate about design and UI/UX. Love teaching Photoshop!",
        "teachSkills": ["Photoshop", "Figma", "UI/UX Design"],
        "learnSkills": ["Python", "Machine Learning"],
        "experienceLevel": "Advanced",
        "availability": ["Mon", "Wed", "Fri"],
        "rating": 4.8,
        "ratingCount": 12,
        "skillCredits": 25,
        "isActive": True
    },
    {
        "uid": "sample_user_2",
        "email": "rahul@example.com",
        "name": "Rahul Verma",
        "college": "NIT Trichy",
        "department": "Information Technology",
        "year": "2nd Year",
        "bio": "Android dev enthusiast. Always up for learning new tech!",
        "teachSkills": ["Kotlin", "Android Dev", "Firebase"],
        "learnSkills": ["Data Science", "React"],
        "experienceLevel": "Intermediate",
        "availability": ["Tue", "Thu", "Sat"],
        "rating": 4.3,
        "ratingCount": 7,
        "skillCredits": 18,
        "isActive": True
    },
    {
        "uid": "sample_user_3",
        "email": "ananya@example.com",
        "name": "Ananya Patel",
        "college": "VIT Vellore",
        "department": "Electronics",
        "year": "4th Year",
        "bio": "Machine Learning researcher. Love teaching Python and data science.",
        "teachSkills": ["Python", "Machine Learning", "Data Science"],
        "learnSkills": ["Photoshop", "Video Editing"],
        "experienceLevel": "Advanced",
        "availability": ["Mon", "Tue", "Wed", "Thu"],
        "rating": 4.9,
        "ratingCount": 20,
        "skillCredits": 42,
        "isActive": True
    },
    {
        "uid": "sample_user_4",
        "email": "karan@example.com",
        "name": "Karan Singh",
        "college": "BITS Pilani",
        "department": "Computer Science",
        "year": "1st Year",
        "bio": "Guitar player and Flutter developer. Love music and code!",
        "teachSkills": ["Guitar", "Flutter", "JavaScript"],
        "learnSkills": ["Python", "Figma", "Public Speaking"],
        "experienceLevel": "Beginner",
        "availability": ["Sat", "Sun"],
        "rating": 3.8,
        "ratingCount": 4,
        "skillCredits": 10,
        "isActive": True
    },
    {
        "uid": "sample_user_5",
        "email": "meera@example.com",
        "name": "Meera Nair",
        "college": "IIT Bombay",
        "department": "Design",
        "year": "2nd Year",
        "bio": "Graphic designer and content creator. Can teach video editing!",
        "teachSkills": ["Video Editing", "Photoshop", "Content Writing"],
        "learnSkills": ["Kotlin", "Android Dev", "Firebase"],
        "experienceLevel": "Intermediate",
        "availability": ["Mon", "Wed", "Fri", "Sun"],
        "rating": 4.5,
        "ratingCount": 9,
        "skillCredits": 31,
        "isActive": True
    }
]

def seed_users():
    print("Seeding users...")
    for user in SAMPLE_USERS:
        db.collection("Users").document(user["uid"]).set({
            **user,
            "createdAt": datetime.now(),
            "updatedAt": datetime.now()
        })
        print(f"  ✅ Added: {user['name']}")
    print(f"\nSeeded {len(SAMPLE_USERS)} users successfully!")

if __name__ == "__main__":
    seed_users()
