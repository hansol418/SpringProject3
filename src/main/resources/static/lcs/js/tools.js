document.getElementById('fileInput').addEventListener('change', function(event) {
    const file = event.target.files[0];
    if (file) {
        const formData = new FormData();
        formData.append('imageFile', file);

        // Show image preview
        const reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('imagePreview').src = e.target.result;
        };
        reader.readAsDataURL(file);

        // Send image to server and get the analysis result
        fetch('/analyze', {
            method: 'POST',
            body: formData,
        })
            .then(response => response.json())
            .then(data => {
                document.getElementById('result').innerHTML = `
                        <p>Class: ${data.predicted_class_label}</p>
                        <p>Confidence: ${data.confidence}</p>
                        <p>Details: ${JSON.stringify(data.class_confidences)}</p>
                    `;
            })
            .catch(error => {
                console.error('Error:', error);
                document.getElementById('result').innerText = 'Error analyzing image.';
            });
    }
});