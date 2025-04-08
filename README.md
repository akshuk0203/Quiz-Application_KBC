# Quiz-Application_KBC
A quiz-based KBC (Kaun Banega Crorepati) application using Java, with a fully interactive GUI for user registration, question answering, and game progression.

🚀 Overview

    🎯 This is a fully interactive Quiz Application inspired by the popular show Kaun Banega Crorepati (KBC). It features:
        
    👤 User registration
    🧠 Level-wise MCQ quiz gameplay
    💡 Lifelines support
    💸 Winnings tracking
    🕒 60-second timer per question
    
📋 System Requirements

    Java Development Kit (JDK): 8 or higher
    MySQL Database: 5.7 or higher
    MySQL Connector/J: 8.0 or higher

🗂️Create a database named 'kbc'
     
  *The following tables are used in the application:*

    'user' Table

    id (Primary Key): Unique identifier for each user.
    username: Name of the user.

    'questionbank' Table

    SrNo (Primary Key): Unique identifier for each question.
    question: The quiz question.
    answer: Correct answer for the question.
    optionA, optionB, optionC, optionD: Multiple choice options.
    flag: To track whether question has been displayed previously.
    difficulty: Indicates difficulty level or question category.

    'Winnings' Table

    id (Primary Key): Unique identifier for each record in the winnings table.
    user_id: Reference to the user's id field in the User table.
    amount_won: Total amount won by the user in a particular game.
    win_date: Current Date 

  🎮 How to Play

    Register as a new user.
    Start the quiz — questions will be shown level-wise.
    60 seconds timer per question.
    Lifeline system available:
        🧩 50-50 → Two wrong options are removed.
        🧠 Expert Advice → System gives the right answer.
        🎯 Double Dip → Two chances to pick the correct option.
    💥 Wrong answer? Game ends and shows your total winnings.
    🧾 User’s game history is saved in the database.
