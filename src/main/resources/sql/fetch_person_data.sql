CREATE OR REPLACE PROCEDURE fetch_person_data IS
    TYPE person_rec_type IS RECORD (
        inn person.inn%TYPE,
        passport_number person.passport_number%TYPE,
        passport_series person.passport_series%TYPE,
        phone_number person.phone_number%TYPE,
        ogrn person.ogrn%TYPE,
        ogrnip person.ogrnip%TYPE,
        snils person.snils%TYPE,
        name person.name%TYPE,
        middle_name person.middle_name%TYPE,
        last_name person.last_name%TYPE,
        birthdate person.birthdate%TYPE
    );
    person_rec person_rec_type;

    TYPE credit_history_rec_type IS RECORD (
        inn person_credit_history.inn%TYPE,
        credit_score person_credit_history.credit_score%TYPE,
        credit_date person_credit_history.credit_date%TYPE
    );
    credit_history_rec credit_history_rec_type;

    TYPE application_history_rec_type IS RECORD (
        inn person_application_history.inn%TYPE,
        application_date person_application_history.application_date%TYPE,
        application_status person_application_history.application_status%TYPE
    );
    application_history_rec application_history_rec_type;

    TYPE credit_requests_history_rec_type IS RECORD (
        inn persons_credit_requests_history.inn%TYPE,
        amount_requested persons_credit_requests_history.amount_requested%TYPE,
        request_date persons_credit_requests_history.request_date%TYPE
    );
    credit_requests_history_rec credit_requests_history_rec_type;

    CURSOR person_cursor IS SELECT * FROM person;
    CURSOR credit_history_cursor(p_inn VARCHAR2) IS SELECT * FROM person_credit_history WHERE inn = p_inn;
    CURSOR application_history_cursor(p_inn VARCHAR2) IS SELECT * FROM person_application_history WHERE inn = p_inn;
    CURSOR credit_requests_history_cursor(p_inn VARCHAR2) IS SELECT * FROM persons_credit_requests_history WHERE inn = p_inn;

    total_credit_score NUMBER;
    total_applications NUMBER;
    total_credit_requested NUMBER;

BEGIN
    FOR person_rec IN person_cursor LOOP
        DBMS_OUTPUT.PUT_LINE('Processing Person INN: ' || person_rec.inn);
        total_credit_score := 0;
        total_applications := 0;
        total_credit_requested := 0;

        FOR credit_history_rec IN credit_history_cursor(person_rec.inn) LOOP
            total_credit_score := total_credit_score + credit_history_rec.credit_score;
            DBMS_OUTPUT.PUT_LINE('Credit Score on ' || credit_history_rec.credit_date || ': ' || credit_history_rec.credit_score);
        END LOOP;

        FOR application_history_rec IN application_history_cursor(person_rec.inn) LOOP
            total_applications := total_applications + 1;
            DBMS_OUTPUT.PUT_LINE('Application on ' || application_history_rec.application_date || ' Status: ' || application_history_rec.application_status);
        END LOOP;

        FOR credit_requests_history_rec IN credit_requests_history_cursor(person_rec.inn) LOOP
            total_credit_requested := total_credit_requested + credit_requests_history_rec.amount_requested;
            DBMS_OUTPUT.PUT_LINE('Credit Requested on ' || credit_requests_history_rec.request_date || ': ' || credit_requests_history_rec.amount_requested);
        END LOOP;

        DBMS_OUTPUT.PUT_LINE('Total Credit Score: ' || total_credit_score);
        DBMS_OUTPUT.PUT_LINE('Total Applications: ' || total_applications);
        DBMS_OUTPUT.PUT_LINE('Total Credit Requested: ' || total_credit_requested);
        DBMS_OUTPUT.PUT_LINE('-----------------------');
    END LOOP;
END fetch_person_data;
/