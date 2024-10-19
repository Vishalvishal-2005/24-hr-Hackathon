import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
import pickle

# Load your dataset
df = pd.read_csv('E:/New folder/bc/backend/src/main/resources/models/Housing.csv')

# Define features and target
X = df[['area', 'price', 'bedrooms', 'bathrooms']]
y = df['area']

# Split the dataset
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Train the model
model = LinearRegression()
model.fit(X_train, y_train)

# Serialize the model
with open('E:/New folder/bc/backend/src/main/resources/models/emission_model.pkl', 'wb') as f:
    pickle.dump(model, f)

print("Model saved successfully as 'emission_model.pkl'")
