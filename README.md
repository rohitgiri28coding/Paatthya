
---

# 🏆 Paatthya - Your Personalized Coaching Companion

**Paatthya** is an Android coaching app built using **Jetpack Compose** to provide a seamless, modern, and interactive learning experience. The app allows students to watch lectures, stay updated with notices, check live batches, view results, and access important sections like **Contact Us**, **Terms & Conditions**, **Gallery**, and **About Us**.

Paatthya utilizes **Firebase Authentication** for secure login/signup (including Google Sign-In and One-Tap Sign-In), and **Firestore** for managing user data and notices.

---

## 📌 Features

### 🎓 Student-Facing Features

✅ **Watch Lectures** – Play YouTube videos within the app for uninterrupted learning.
✅ **Notice Section** – Get the latest coaching-related updates (only admins can post).
✅ **Contact Us** – Direct call, email, website, or Google Maps location of the institute.
✅ **Easy Login & Signup** – Email/password or Google Sign-In (One-Tap supported).
✅ **Live Batches** – View all active and running coaching batches.
✅ **Result Section** – Easily access exam results in-app.
✅ **Gallery Section** – Browse images from coaching events and milestones.
✅ **About Section** – Learn more about the coaching institute.
✅ **Terms & Conditions** – Read all policies and usage guidelines.
✅ **Secure & Private** – Firebase Authentication ensures safe and encrypted access.

### 🆕 Newly Added Features

🆕 **Lecture Upload by Teachers** – Teachers can now upload lecture videos (stored on AWS S3) from the **Paatthya Teacher App**.
🆕 **Lecture Categorization** – Students can view lectures organized by batch and subject for easier navigation.
🆕 **PDF & File Access** – Students can now access study materials such as PDFs and images uploaded by teachers.
🆕 **Offline Support** – Essential data like recent lectures and notices are cached locally for offline viewing.
🆕 **Role-Based Access Control** – Separate apps and permissions for students and teachers ensure streamlined workflows.
🆕 **Firestore + S3 Hybrid Storage** – Metadata is stored in Firestore, while actual media files are hosted securely on Amazon S3.

---

## 🚀 Tech Stack

| Component            | Technology                        |
| -------------------- | --------------------------------- |
| Android UI           | Jetpack Compose                   |
| Architecture         | MVVM (Model-View-ViewModel)       |
| Database             | Firestore (Cloud Firestore)       |
| Authentication       | Firebase (Email, Google, One-Tap) |
| Media Hosting        | Amazon S3                         |
| Networking           | Retrofit                          |
| State Management     | Kotlin Flow, LiveData             |
| Dependency Injection | Hilt                              |

---

## 🛠 Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/rohitgiri28coding/Paatthya.git
   ```
2. Open in **Android Studio** (latest version recommended).
3. Configure your Firebase project:

   * Enable Firestore and Authentication.
4. Add your Firebase and AWS S3 credentials as required.
5. Build and run using an emulator or physical device.

---

## 📝 Contributing

We welcome contributions!
**Steps to contribute:**

* Fork the repository
* Create a new branch: `feature/your-feature-name`
* Commit your changes
* Push to your fork
* Open a Pull Request 🎉

---

## 📧 Contact

For queries, feedback, or feature requests:
📩 Email: [rohitgiri28coding.com](mailto:rohitgiri28coding.com)

---

🔹 **Paatthya – Transform Your Learning Experience!**

---

