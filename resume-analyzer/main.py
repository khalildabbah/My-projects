import streamlit as st
import PyPDF2
import os
import io
from openai import OpenAI
from dotenv import load_dotenv

# Load environment variables
load_dotenv()

# App config
st.set_page_config(page_title="Resume Analyzer", page_icon=":guardsman:", layout="centered")

# UI Header
st.title("📄 AI Resume Analyzer")
st.markdown("Welcome to the **AI-powered resume analyzer**! 🧠")
st.markdown("Upload your resume and get feedback on its **content**, **structure**, and **fit for a job role**.")
st.markdown("_This app uses OpenAI's GPT-3.5 to analyze your resume and provide actionable suggestions._")

# API Key
OpenAI_API_KEY = os.getenv("OPENAI_API_KEY")

# Upload + Input
uploaded_file = st.file_uploader("📎 Upload your resume (PDF or TXT)", type=["pdf", "txt"])
job_role = st.text_input("💼 Enter the job role you are applying for", placeholder="e.g. Software Engineer, Data Scientist")
analyze = st.button("🔍 Analyze Resume")

# PDF extractor
def extract_text_from_pdf(pdf_file):
    pdf_reader = PyPDF2.PdfReader(pdf_file)
    text = ""
    for page in pdf_reader.pages:
        text += page.extract_text() + "\n"
    return text

# General file extractor
def extract_text_from_file(uploaded_file):
    if uploaded_file.type == "application/pdf":
        return extract_text_from_pdf(io.BytesIO(uploaded_file.read()))
    return uploaded_file.read().decode("utf-8")

# Process
if analyze and uploaded_file:
    with st.spinner("Analyzing your resume... This may take a few seconds ⏳"):
        try:
            file_content = extract_text_from_file(uploaded_file)

            if not file_content.strip():
                st.error("🚫 The uploaded file is empty. Please upload a valid resume.")
                st.stop()

            prompt = f"""
            You are an expert resume reviewer. You will be given a resume and a job role.
            Your task is to analyze the resume and provide feedback on its content and structure.
            Please provide suggestions for improvement and highlight any areas that need attention.
            Specifically, improvements for {job_role if job_role else 'general job applications'}.
            Resume content:
            {file_content}
            Please provide your analysis in a clear and structured format with specific recommendations.
            """

            client = OpenAI(api_key=OpenAI_API_KEY)
            response = client.chat.completions.create(
                model="gpt-3.5-turbo",
                messages=[
                    {"role": "system", "content": "You are an expert resume reviewer with years of experience in HR and recruitment."},
                    {"role": "user", "content": prompt}
                ],
                temperature=0.7,
                max_tokens=1000
            )

            analysis_text = response.choices[0].message.content

            st.success("✅ Resume analyzed successfully!")
            st.markdown("### 📝 Analysis Results")
            st.markdown(analysis_text)

            # 💾 Download Button
            st.download_button(
                label="📥 Download Analysis",
                data=analysis_text,
                file_name="resume_analysis.txt",
                mime="text/plain"
            )

        except Exception as e:
            st.error(f"❌ An error occurred while processing the file: {str(e)}")
            st.stop()

elif analyze and not uploaded_file:
    st.warning("⚠️ Please upload a resume before clicking 'Analyze Resume'.")
