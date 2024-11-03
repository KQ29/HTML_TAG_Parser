# Data Cleaning and Export Script

### This Python script connects to a PostgreSQL database, retrieves data from a specified table, cleans it by removing HTML tags and control characters, and exports the cleaned data to a CSV file.

## Requirements
**To run this script, you'll need**:

- **Python 3.x**
- **PostgreSQL database**
- **Required packages**: Install using pip install psycopg2 beautifulsoup4 tqdm

## Installation
**Install the required dependencies**:

```bash

pip install psycopg2 beautifulsoup4 tqdm
```

## Usage
**Run the script with the following command, specifying the necessary parameters**:

#### MacOS
``` bash

python3 your_code.py --hostname <your_localhost> --database <your_database> --username <your_username> --password <your_password> --port <your_port> --input <your_table_name> --output cleaned_data.csv
```

#### Windows
``` bash

python your_code.py --hostname <your_localhost> --database <your_database> --username <your_username> --password <your_password> --port <your_port> --input <your_table_name> --output cleaned_data.csv
```

## Arguments

```bash
--hostname: Database host (e.g., localhost)
--database: Database name
--username: Username for database access
--password: Password for database access
--port: Database port (e.g., 5432 for PostgreSQL)
--input: Name of the input table in the database
--output: Path to the output CSV file
```

## Script Functionality
- **Database Connection**: Connects to the PostgreSQL database with the specified parameters.
- **Data Retrieval**: Fetches all rows from the specified table.

## Data Cleaning:
- Uses BeautifulSoup to remove HTML tags.
- Strips control characters and extra spaces.
- Export to CSV: Writes the cleaned data to the specified CSV file.

### Example Command
``` bash
python3 your_code.py --hostname localhost --database my_database --username my_user --password my_password --port 5432 --input my_table --output cleaned_data.csv
```
## Notes
This script requires a valid PostgreSQL database connection.
Control characters are removed from the text, and only cleaned text is exported.
