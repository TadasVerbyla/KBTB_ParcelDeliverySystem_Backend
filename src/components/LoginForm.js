import React, { useState } from 'react';
import axios from 'axios';

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Prepare headers for the request
        const auth = btoa(`${username}:${password}`);
        const headers = {
            'Authorization': `Basic ${auth}`
        };

        try {
            // Replace 'http://localhost:8080/protected-endpoint' with your endpoint
            const response = await axios.get('http://localhost:8080/api/V1/parcel', { headers });

            // If the request is successful, your user is authenticated.
            console.log(response.data);
        } catch (error) {
            // Handle error
            console.error('An error occurred:', error.response);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Username:
                <input type="text" value={username} onChange={e => setUsername(e.target.value)} />
            </label>
            <label>
                Password:
                <input type="password" value={password} onChange={e => setPassword(e.target.value)} />
            </label>
            <input type="submit" value="Log In" />
        </form>
    );
};

export default LoginForm;
