from flask import Flask, request, jsonify
import google.generativeai as genai
from flask_cors import CORS
app = Flask(__name__)
CORS(app)

# Load your Gemini API key securely
genai.configure(api_key="AIzaSyB1pR1tUxnabFNqEgRWmiBQ8XSSH7VEsb0")

model = genai.GenerativeModel("gemini-1.5-flash")


@app.route("/generate-sql", methods=["POST"])
def generate_sql():
    data = request.get_json()

    natural_language_query = data.get("natural_language", "").strip()
    if not natural_language_query:
        return jsonify({"error": "Missing natural_language input"}), 400

    prompt = (
        """Generate an SQL query based on the following natural language input. 
Return ONLY the raw SQL code as a single-line string.
"table names are product,order,customer"
"please use table name as order instead of orders"
""
 "You are an expert SQL assistant. "
    "Assume you have three tables: `Customer(id, name,email)` and `Order(id,customer_id,List<> of product,date) and product(id,name,price) `. "
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
