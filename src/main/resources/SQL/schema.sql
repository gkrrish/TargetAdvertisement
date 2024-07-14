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

CREATE TABLE TARGETED_ADVERTISEMENTS (
    advertisement_id INT PRIMARY KEY,
    advertiser_id INT NOT NULL,
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
    FOREIGN KEY (advertiser_id) REFERENCES ADVERTISER_DETAILS(advertiser_id),
    FOREIGN KEY (location_id) REFERENCES MASTER_STATEWISE_LOCATIONS(location_id)
);



