from flask import Flask, request, jsonify
import google.generativeai as genai
import os

# Load your Gemini API key securely
genai.configure(api_key="AIzaSyB1pR1tUxnabFNqEgRWmiBQ8XSSH7VEsb0")

model = genai.GenerativeModel("gemini-1.5-flash")

app = Flask(__name__)

@app.route("/generate-sql", methods=["POST"])
def generate_sql():
    data = request.get_json()

    natural_language_query = data.get("natural_language", "").strip()
    if not natural_language_query:
        return jsonify({"error": "Missing natural_language input"}), 400

    prompt = (
        """Generate an SQL query based on the following natural language input. 
Return ONLY the raw SQL code as a single-line string.
 "You are an expert SQL assistant. "
    "Write a SQL query to return customer IDs and names of customers who have placed more than 5 orders. "
    "Assume you have two tables: `Customers(customer_id, customer_name, ...)` and `Orders(order_id, customer_id, ...)`. "
DO NOT include any markdown formatting like triple backticks (```), newlines, or explanations."""
        f"{natural_language_query}"
    )

    try:
        response = model.generate_content(prompt)
        return jsonify({"sql_query": response.text.strip()})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == "__main__":
    app.run(debug=True)
