(function () {
    document.addEventListener('DOMContentLoaded', () => {
        const courseInputs = document.querySelectorAll('.car-course');
        const endpointURL = '/cars/update-course';

        courseInputs.forEach(inputElement => {
            inputElement.addEventListener('change', (event) => {
                const changedField = event.target;
                const carId = changedField.dataset.carId;
                const newCourse = changedField.value;
                console.log("dataset:", changedField.dataset);

                const params = new URLSearchParams({
                    id: carId,
                    course: newCourse
                }).toString();

                if (!carId || isNaN(newCourse)) {
                    console.error("Missing car ID or invalid course value.");
                    return;
                }

                changedField.disabled = true;
                changedField.style.backgroundColor = 'yellow';

                fetch(`${endpointURL}?${params}`, {
                    method: 'POST',
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`Server error (Status ${response.status})`);
                        }
                        return response.text();
                    })
                    .then(message => {
                        console.log("Save successful!", message);
                        changedField.style.backgroundColor = 'lightgreen';
                        setTimeout(() => changedField.style.backgroundColor = '', 1000);
                        setTimeout(() => {
                            location.reload();
                        }, 1000);
                    })
                    .catch(error => {
                        console.error('Data saving error:', error);
                        changedField.style.backgroundColor = 'salmon'; // Error
                        alert(`Failed to save course for car ID ${carId}.`);
                    })
                    .finally(() => {
                        changedField.disabled = false;
                    });
            });
        });
    });
})();