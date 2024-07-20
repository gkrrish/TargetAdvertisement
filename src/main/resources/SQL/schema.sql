CREATE TABLE ADVERTISER_DETAILS (
    advertiser_id INT PRIMARY KEY,
    UserID NUMBER(10), -- Foreign key to USER_DETAILS, nullable
    isUser CHAR(1) DEFAULT 'N' CHECK (isUser IN ('Y', 'N')),
    advertiser_name VARCHAR2(255) NOT NULL,
    advertiser_mobile_number VARCHAR2(15),
    contact_details VARCHAR2(512),
    status VARCHAR2(10) CHECK (status IN ('active', 'inactive')),
    FOREIGN KEY (UserID) REFERENCES USER_DETAILS(UserID)
);

CREATE TABLE TARGETED_ADVERTISEMENT_PLAN (
    advertisement_id INT PRIMARY KEY,
    location_id INT NOT NULL,
    media_type VARCHAR2(50) CHECK (media_type IN ('audio', 'video', 'image', 'pdf')),
    file_length NUMBER(10), -- length in seconds for audio/video
    file_size NUMBER(10), -- size in kilobytes
    user_count INT NOT NULL, -- target user count
    price DECIMAL(10, 2) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    advertisement_type VARCHAR2(50),
    amount DECIMAL(10, 2),
    tariff_offer VARCHAR2(255),
    FOREIGN KEY (location_id) REFERENCES MASTER_STATEWISE_LOCATIONS(location_id)
);

CREATE TABLE TARGETED_ADVERTISEMENTS (
    TargetAdvtId INT PRIMARY KEY,
    FileLocation VARCHAR2(1055) NOT NULL,
    createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    paid CHAR(1) CHECK (paid IN ('Y', 'N')),
    paid_amount DECIMAL(10, 2) CHECK (paid_amount >= 0),
    VerifiedByAdmin CHAR(1) CHECK (VerifiedByAdmin IN ('Y', 'N')),
    AdminName VARCHAR2(255),
    VerifiedTimestamp TIMESTAMP,
    VerificationREMARKS VARCHAR2(255),
    verified_allow_to_publish CHAR(1) CHECK (verified_allow_to_publish IN ('Y', 'N')),
    advertisement_id INT,
    advertiser_id INT,
    batchId NUMBER,
    FOREIGN KEY (advertisement_id) REFERENCES TARGETED_ADVERTISEMENT_PLAN(advertisement_id),
    FOREIGN KEY (advertiser_id) REFERENCES ADVERTISER_DETAILS(advertiser_id),
    FOREIGN KEY (batchId) REFERENCES MASTER_BATCH_JOBS(BATCH_ID)
);



