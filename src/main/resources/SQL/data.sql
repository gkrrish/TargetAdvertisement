INSERT INTO advertiser_details (
    advertiser_id,
    userid,
    isuser,
    advertiser_name,
    advertiser_mobile_number,
    contact_details,
    status
) VALUES (
    1,
    1,
    'Y',
    'Krishna Gaganam',
    '+91987654322',
    'Hyderabad',
    'active'
);

INSERT INTO advertiser_details (
    advertiser_id,
    isuser,
    advertiser_name,
    advertiser_mobile_number,
    contact_details,
    status
) VALUES (
    2,
    'N',
    'Jane Smith',
    '+919876543215',
    'janesmith@advertiser.com, Hyderabad',
    'active'
);


INSERT INTO TARGETED_ADVERTISEMENTS (advertisement_id, advertiser_id, location_id, media_type, file_length, file_size, user_count, price, start_time, end_time, advertisement_type, amount, tariff_offer)
VALUES (1, 1, 1, 'video', 30, 5000, 100, 100.00, TO_TIMESTAMP('2024-07-14 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-07-14 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'Promotional', 50.00, 'Discount 10%');
