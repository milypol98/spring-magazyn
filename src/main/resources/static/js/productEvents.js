document.addEventListener('DOMContentLoaded', function() {

    // --- Referencje do elementów formularza ---
    const eventTypeSelect = document.getElementById('eventType');

    // Grupy pól
    const grpFromCar = document.getElementById('grp-fromCar');
    const grpToCar = document.getElementById('grp-toCar');
    const allLocationGroups = [
        grpFromCar, grpToCar,
    ];

    // Pola select
    const fromTypeSelect = document.getElementById('fromType');
    const toTypeSelect = document.getElementById('toType');

    function showGroup(groupElement) {
        if (groupElement) groupElement.style.display = '';
    }

    /**
     * Główna funkcja sterująca formularzem.
     */
    function updateFormVisibility() {
        allLocationGroups.forEach(g => g.style.display = 'none');
        const selectedEvent = eventTypeSelect.value;
        switch (selectedEvent) {
            case 'DELIVERY': // Dostawa -> tylko Cel (TO)
                fromTypeSelect.value = '';
                toTypeSelect.value = 'WAREHOUSE';
                break;

            case 'TRANSFER': // Magazyn -> Auto
                fromTypeSelect.value = 'WAREHOUSE';
                toTypeSelect.value = 'CAR';
                showGroup(grpToCar);
                break;

            case 'RETURN':   // Auto -> Magazyn
                fromTypeSelect.value = 'CAR';
                toTypeSelect.value = 'WAREHOUSE';
                showGroup(grpFromCar);
                break;

            case 'TRANSFER_CAR_TO_CAR': // Auto -> Auto
                fromTypeSelect.value = 'CAR';
                toTypeSelect.value = 'CAR';
                showGroup(grpFromCar);
                showGroup(grpToCar);
                break;
            case 'LOST_IN_CAR':   // Utracone -> AUTO
                fromTypeSelect.value = 'CAR';
                toTypeSelect.value = '';
                showGroup(grpFromCar);
                break;
            case 'LOST_IN_WAREHOUSES': // Utracone -> Magazyn
                fromTypeSelect.value = 'WAREHOUSE';
                toTypeSelect.value = '';
                break;
        }
    }
    eventTypeSelect.addEventListener('change', updateFormVisibility);
    updateFormVisibility();
});

