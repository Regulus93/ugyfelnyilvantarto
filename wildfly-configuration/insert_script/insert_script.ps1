$runningPath = Split-Path -Parent $PSCommandPath
Set-Location "C:\Program Files\PostgreSQL\9.5\bin\"
.\psql.exe -a -f "$runningPath\insert_person_table.sql" -U postgres "dbname=clientregistry password=admin"
.\psql.exe -a -f "$runningPath\insert_address.sql" -U postgres "dbname=clientregistry password=admin"
.\psql.exe -a -f "$runningPath\insert_user_table.sql" -U postgres "dbname=clientregistry password=admin"
.\psql.exe -a -f "$runningPath\insert_connection_channel.sql" -U postgres "dbname=clientregistry password=admin"
.\psql.exe -a -f "$runningPath\insert_num_item_per_page_table.sql" -U postgres "dbname=clientregistry password=admin"
.\psql.exe -a -f "$runningPath\insert_role_table.sql" -U postgres "dbname=clientregistry password=admin"
.\psql.exe -a -f "$runningPath\insert_company_table.sql" -U postgres "dbname=clientregistry password=admin"
.\psql.exe -a -f "$runningPath\insert_project_table.sql" -U postgres "dbname=clientregistry password=admin"
.\psql.exe -a -f "$runningPath\insert_company_project_table.sql" -U postgres "dbname=clientregistry password=admin"
.\psql.exe -a -f "$runningPath\insert_event_table.sql" -U postgres "dbname=clientregistry password=admin"
.\psql.exe -a -f "$runningPath\insert_note_table.sql" -U postgres "dbname=clientregistry password=admin"
.\psql.exe -a -f "$runningPath\insert_invitation_table.sql" -U postgres "dbname=clientregistry password=admin"
.\psql.exe -a -f "$runningPath\insert_user_event_table.sql" -U postgres "dbname=clientregistry password=admin"
.\psql.exe -a -f "$runningPath\insert_contactperson_table.sql" -U postgres "dbname=clientregistry password=admin"
Set-Location $runningPath