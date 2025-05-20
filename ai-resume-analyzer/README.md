# Resume Analyzer

This is a simple AI-powered app built with Streamlit that analyzes your resume and gives feedback using OpenAI's GPT-3.5 or any LLM model with an API key .

You can upload your resume (PDF or TXT), enter the job title you're applying for, and the app will generate suggestions to improve your content and structure.

---

## How to Use

```bash
# 1. Clone the repository
git clone https://github.com/khalildabbah/My-projects.git
cd My-projects/resume-analyzer

# 2. Create and activate a virtual environment
# On Windows:
python -m venv .venv
.venv\Scripts\activate

# On macOS/Linux:
# python3 -m venv .venv
# source .venv/bin/activate

# 3. Install dependencies
pip install -r requirements.txt

# 4. Set your OpenAI API key
# Create a file named `.env` and add the following line:
echo OPENAI_API_KEY=your_openai_api_key_here > .env

# 5. Run the app
streamlit run main.py
```

---

## Notes

- The app supports resumes in PDF and TXT format.
- You’ll need an OpenAI API key to use GPT.
- GPT-3.5 provides smart suggestions, but always review and refine the output yourself.

---

## License

This project is for learning and personal use.
