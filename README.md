---

# 🌐 Social Media Platform Backend API

### **Modular Monolith & Production-Ready Architecture Plan**

> A scalable, event-driven **Social Media Backend** designed as a **Modular Monolith**, divided into five cohesive domain modules.
> Built for — using **Spring Boot 3.5.7, Java 21, MySQL, Redis, Kafka, Elasticsearch, and Google Cloud**.
> Later this project will be breaked to **Microservices**.

---

## 🧩 1. Core Engineering Principles

| **Principle**                 | **Meaning**                                 | **Example in Social Platform**                            |
| ----------------------------- | ------------------------------------------- | --------------------------------------------------------- |
| **S – Single Responsibility** | Each module owns a single domain            | Auth, Users, Content, Interactions, Admin                 |
| **O – Open/Closed**           | Extend modules without modifying internals  | Add new notification types without changing core logic    |
| **L – Liskov Substitution**   | Derived classes can replace base            | `NotificationStrategy` for push/email/in-app              |
| **I – Interface Segregation** | Use focused contracts                       | `FeedGenerator`, `NotificationPublisher`, `SearchIndexer` |
| **D – Dependency Inversion**  | Depend on abstractions, not implementations | `PostEventPublisher` interface used by multiple modules   |

---

## 🧱 2. Modular Monolith Philosophy

* A **single deployable artifact**, internally organized into five business domains
* Each module owns its **entities, services, and database schema**
* **Cross-module communication** only via **domain events (Kafka)** and **interface contracts**
* Enables **horizontal scalability** and **future microservice decomposition**

---

## 🗂️ 3. Project Structure

```
com.socialapp/
│
├── authmodule/             # Authentication, MFA, Sessions, JWT
├── usermodule/             # Profiles, Follows, Privacy, Settings
├── contentmodule/          # Posts, Media, Feeds, Search
├── interactionmodule/      # Comments, Likes, Messaging, Notifications
├── adminmodule/            # Moderation, Reports, Analytics
└── infra/                  # Shared Infra: DB, Kafka, Redis, Logging, S3, Elastic
```

---

## ⚙️ 4. Technology Stack

| **Layer**             | **Tech / Tool**                                         |
| --------------------- | ------------------------------------------------------- |
| **Backend Framework** | Java 21 + Spring Boot 3.5.7                             |
| **Database**          | MySQL (RDBMS)                                           |
| **Cache / Async**     | Redis (cache, rate-limit), Kafka (events)               |
| **Search / Storage**  | Elasticsearch (search), GCP storage (media)             |
| **Security**          | JWT, OAuth2                                             |
| **CI/CD**             | GitHub Actions / GitLab CI + Docker + Helm + Kubernetes |

---

## 🔒 5. AUTHMODULE — Authentication & Security

**Purpose:**
Handles registration, authentication, tokens, MFA, and access control.

**Entities:**
`UserCredential`, `Role`, `Session`, `VerificationToken`

**Key Features:**

* JWT + refresh rotation
* MFA (TOTP/OTP)
* Argon2id password hashing
* Rate limiting via Redis
* Account lockout & Google/Facebook OAuth

**API Endpoints:**

| **Method** | **Endpoint**       | **Description**       |
| ---------- | ------------------ | --------------------- |
| `POST`     | `/auth/register`   | Register new account  |
| `POST`     | `/auth/login`      | Login and issue JWT   |
| `POST`     | `/auth/refresh`    | Refresh JWT token     |
| `POST`     | `/auth/logout`     | Revoke active session |
| `POST`     | `/auth/mfa/verify` | Verify MFA code       |

---

## 👤 6. USERMODULE — Profiles & Social Graph

**Purpose:**
Manages user identity, relationships, and visibility settings.

**Entities:**
`UserProfile`, `FollowRelation`, `BlockRelation`, `UserSettings`

**Features:**

* Follow / Unfollow users
* Block / Unblock users
* Privacy modes (Public / Private)
* “People You May Know” graph suggestions
* S3-stored avatars and banners

**API Endpoints:**

| **Method** | **Endpoint**          | **Description**       |
| ---------- | --------------------- | --------------------- |
| `GET`      | `/users/{id}`         | Retrieve user profile |
| `PUT`      | `/users/{id}`         | Update profile        |
| `POST`     | `/users/{id}/follow`  | Follow user           |
| `DELETE`   | `/users/{id}/unfollow`| Unfollow user         |
| `POST`     | `/users/{id}/block`   | Block user            |
| `GET`      | `/users/suggestions`  | Recommended users     |

---

## 📝 7. CONTENTMODULE — Posts, Media & Feeds

**Purpose:**
Handles posts, hashtags, media uploads, feeds, and search indexing.

**Entities:**
`Post`, `MediaAsset`, `Hashtag`, `FeedItem`

**Features:**

* Post creation (text, image, video)
* Feed caching in Redis
* Hashtag/mention extraction
* Full-text search (Elasticsearch)
* Trending algorithm via likes/comments/time

**API Endpoints:**

| **Method** | **Endpoint**    | **Description**             |
| ---------- | --------------- | --------------------------- |
| `POST`     | `/posts`        | Create new post             |
| `GET`      | `/posts/{id}`   | Get post details            |
| `DELETE`   | `/posts/{id}`   | Delete post                 |
| `GET`      | `/feed/home`    | Personalized feed           |
| `GET`      | `/feed/explore` | Explore/trending posts      |
| `GET`      | `/search?q=`    | Search users/posts/hashtags |

---

## 💬 8. INTERACTIONMODULE — Comments, Likes & Messaging

**Purpose:**
Manages all forms of social interaction and notifications.

**Entities:**
`Comment`, `Like`, `Notification`, `MessageThread`, `Message`

**Features:**

* Threaded comments & replies
* Real-time DMs (WebSocket)
* Like counter caching (Redis)
* Push notifications (Firebase/APNs)
* Event-driven notification delivery (Kafka)
* Typing indicators & read receipts

**API Endpoints:**

| **Method** | **Endpoint**           | **Description**     |
| ---------- | ---------------------- | ------------------- |
| `POST`     | `/comments`            | Add comment         |
| `GET`      | `/posts/{id}/comments` | Get post comments   |
| `POST`     | `/likes`               | Like/unlike content |
| `GET`      | `/notifications`       | Fetch notifications |
| `POST`     | `/messages`            | Send message        |
| `GET`      | `/messages/threads`    | Retrieve threads    |

---

## 🧑‍💼 9. ADMINMODULE — Moderation & Analytics

**Purpose:**
Gives moderators tools for governance, abuse handling, and analytics.

**Entities:**
`AdminUser`, `Report`, `ModerationAction`, `PlatformStats`

**Features:**

* User suspension / banning
* Report triage queue
* Post/comment removal
* Audit logging of actions
* NSFW/profanity AI detection
* Dynamic feature toggles

**API Endpoints:**

| **Method** | **Endpoint**                  | **Description**      |
| ---------- | ----------------------------- | -------------------- |
| `POST`     | `/admin/auth/login`           | Admin login          |
| `GET`      | `/admin/reports`              | List pending reports |
| `POST`     | `/admin/reports/{id}/resolve` | Resolve report       |
| `POST`     | `/admin/users/{id}/suspend`   | Suspend user         |
| `DELETE`   | `/admin/posts/{id}`           | Remove post          |

---

## 🏗️ 10. INFRA — Shared Infrastructure

| **Component**                   | **Purpose**                        |
| ------------------------------- | ---------------------------------- |
| **MySQL**                       | Primary database (ACID, relations) |
| **Redis**                       | Cache (feeds, sessions, likes)     |
| **Kafka**                       | Event streaming between modules    |
| **Elasticsearch**               | Full-text search engine            |
| **GCP Storage**                 | Media storage (images, videos)     |
| **Spring Actuator**             | Health and metrics endpoints       |

**Shared Utilities:**

* `BaseEntity` (UUID, timestamps)
* Global exception handler
* DTOs, Mappers (MapStruct)
* Kafka event abstraction
* Standardized API response format

---

## 🧠 11. Security & Privacy

| **Feature**      | **Implementation**                 |
| ---------------- | ---------------------------------- |
| Authentication   | JWT + Refresh tokens               |
| Authorization    | Role-based (USER, VERIFIED, ADMIN) |
| MFA              | TOTP/OTP support                   |
| Rate Limiting    | Redis-based sliding window         |
| CSRF/CORS        | Configured for trusted frontends   |
| Audit Trails     | Admin & moderation actions         |
| Privacy          | GDPR-compliant export/delete       |

---

## 🔄 12. Example Event Flow

### 🧵 Create Post & Notify Followers

1. `User → /posts` (ContentModule)
2. `PostCreatedEvent` published to Kafka
3. `InteractionModule` → Notify followers
4. `UserModule` updates cached follower feed
5. `ContentModule` indexes post in Elasticsearch

### 💬 Comment & Mention

1. `/comments` → creates `CommentCreatedEvent`
2. `InteractionModule` → Notifies post author + mentioned users
3. `AdminModule` → Scans comment for abuse

### 🚨 Report & Moderate

1. `/reports` → report saved
2. `AdminModule` → queues for review
3. `Moderator` → resolves → `ModerationAction` logged

---

## 🐳 13. CI/CD & Deployment

**Pipeline:**

* Code Quality: Checkstyle, SpotBugs
* Tests: Unit + Integration (Testcontainers)
* Build: JAR → Docker Image
* Vulnerability Scan
* Deploy: Kubernetes (Helm + Canary Release)

**Runtime Stack:**

* 5 module pods
* PostgreSQL + Redis + Kafka + Elastic + S3
* NGINX API Gateway
* Prometheus + Grafana + Loki for monitoring

---

## 🚀 14. Future Enhancements

* AI-powered moderation & recommendations
* 24-hour “Stories” feature
* GraphQL API for mobile clients
* Premium subscription system
* ActivityPub federation (Fediverse)
* Live audio/video rooms
* Marketplace and digital goods

---

## ✅ 15. Summary

| **Layer**             | **Description**                                            |
| --------------------- | ---------------------------------------------------------- |
| **AuthModule**        | Authentication, MFA, sessions, roles                       |
| **UserModule**        | Profiles, follow system, privacy settings                  |
| **ContentModule**     | Posts, feeds, search, hashtags                             |
| **InteractionModule** | Comments, likes, messaging, notifications                  |
| **AdminModule**       | Moderation, analytics, abuse reports                       |
| **Infra**             | Shared infra: DB, Kafka, Redis, Elastic, S3, Observability |

> 💡 Designed for modular growth — **each domain module can evolve into its own microservice** without refactoring core business logic.

---
