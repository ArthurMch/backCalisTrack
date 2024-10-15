const api_url = "localhost/5433";

const user = {
    name: "John Doe",
    email: "johndoe@example.com",
    password: "password123"
};

fetch('http://localhost:5433/account/create', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(user)
})
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log('User created successfully:', data);
    })
    .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
    });
