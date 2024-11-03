import psycopg2
from bs4 import BeautifulSoup
import csv
import argparse
from tqdm import tqdm
import re


def fetch_and_clean_data(hostname, database, username, password, port, input_table):
    conn = None
    try:
        #Connection with PostgreSQL
        conn = psycopg2.connect(
            host=hostname,
            dbname=database,
            user=username,
            password=password,
            port=port
        )
        
        #Necessary function
        cur = conn.cursor()
        
        #Selecting all rows from table
        query = f"SELECT * FROM {input_table}"
        cur.execute(query)
        rows = cur.fetchall()
        
        cleaned_data = []
        
        #Loop that cleans HTML tags from each row and removes control characters
        for row in tqdm(rows, desc="Cleaning data"):
            cleaned_row = []
            for column in row:
                #Convert column to string
                if isinstance(column, str):
                    text = column
                elif isinstance(column, bytes):
                    text = column.decode('utf-8')  #Type for writing
                else:
                    text = str(column)
                
                #BeautifulSoup
                soup = BeautifulSoup(text, 'html.parser')
                cleaned_text = soup.get_text(separator=' ')
                cleaned_text = remove_control_characters(cleaned_text)
                
                #Debugging
                print(f"Cleaned text: '{cleaned_text}'")
                
                #Check if cleaned_text is not empty or only whitespace
                if cleaned_text.strip():
                    cleaned_row.append(cleaned_text.strip())
                else:
                    cleaned_row.append("") 
                    
            cleaned_data.append(cleaned_row)
        
        return cleaned_data
        
    except psycopg2.Error as e:
        print(f"Error connecting to PostgreSQL: {e}")
    finally:

        if conn is not None:
            conn.close()


def remove_control_characters(text):
    #Remove any single character that follows a backslash '\'
    cleaned_text = re.sub(r'\\.', '', text)
    
    #Remove any remaining consecutive spaces
    cleaned_text = re.sub(r'\s+', ' ', cleaned_text)
    
    return cleaned_text.strip()


def write_to_csv(cleaned_data, output):
    try:
        with open(output, 'w', newline='', encoding='utf-8') as csvfile:
            writer = csv.writer(csvfile)
            writer.writerows(cleaned_data)
        print(f"CSV file '{output}' created successfully.")
    except IOError as e:
        print(f"Error writing CSV file: {e}")


def main():

    parser = argparse.ArgumentParser(description='Clean and export data to CSV.')
    parser.add_argument('--hostname', type=str, required=True, help='Database host')
    parser.add_argument('--database', type=str, required=True, help='Database name')
    parser.add_argument('--username', type=str, required=True, help='Database user')
    parser.add_argument('--password', type=str, required=True, help='Database password')
    parser.add_argument('--port', type=int, required=True, help='Database port')
    parser.add_argument('--input', type=str, required=True, help='Input table name')
    parser.add_argument('--output', type=str, required=True, help='Output CSV file path')
    args = parser.parse_args()
   

    data = fetch_and_clean_data(args.hostname, args.database, args.username, args.password, args.port, args.input)
    
    # Write cleaned data to CSV file
    write_to_csv(data, args.output)


if __name__ == '__main__':
    main()
#Mac
#Command for Run the code: python3 your_code.py --hostname (your_localhost) --database (your_dataset)  --username (your_username) --password (your_password) --port (your_port) --input (your_table.name) --output cleaned_data.csv

#Windows
#Command for Run the code: python your_code.py --hostname (your_localhost)  --database (your_dataset)  --username (your_username) --password (your_password) --port (your_port) --input (your_table.name)  --output cleaned_data.csv
