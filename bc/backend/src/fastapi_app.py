from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import pickle
import numpy as np

# Initialize FastAPI app
app = FastAPI()

# Add CORS middleware to allow requests from your frontend
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # Allows all origins
    allow_credentials=True,
    allow_methods=["*"],  # Allows all methods (POST, GET, etc.)
    allow_headers=["*"],  # Allows all headers
)

# Load the trained model
with open('E:/New folder/bc/backend/src/main/resources/models/emission_model.pkl', 'rb') as f:
    model = pickle.load(f)

# Define the input data model for the API
class PredictionInput(BaseModel):
    area: float
    price: float
    bedrooms: int
    bathrooms: int

# Create a prediction route
@app.post("/predict")
def predict(input_data: PredictionInput):
    # Convert input data to numpy array
    input_features = np.array([[input_data.area, input_data.price, input_data.bedrooms, input_data.bathrooms]])

    # Make prediction
    prediction = model.predict(input_features)[0]

    # Return prediction
    return {"carbon_emissions_prediction": prediction}

# Start the server using Uvicorn
if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=3060)
