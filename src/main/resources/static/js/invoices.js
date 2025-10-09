document.addEventListener('DOMContentLoaded', function () {
    // Znajdź elementy formularza w dokumencie HTML
    const netInput = document.getElementById('totalNet');
    const vatRateInput = document.getElementById('vatRate');
    const grossInput = document.getElementById('totalGross');

    // Sprawdź, czy wszystkie potrzebne elementy istnieją na stronie
    if (netInput && vatRateInput && grossInput) {

        // Funkcja do obliczania i wyświetlania kwoty brutto
        function calculateGross() {
            // Zamień wartości tekstowe na liczby (jeśli pole jest puste, przyjmij 0)
            const netValue = parseFloat(netInput.value) || 0;
            const vatRate = parseFloat(vatRateInput.value) || 0;

            // Oblicz kwotę podatku VAT
            const vatAmount = netValue * (vatRate / 100);

            // Oblicz kwotę brutto
            const grossValue = netValue + vatAmount;

            // Zaktualizuj wartość pola brutto, formatując ją do dwóch miejsc po przecinku
            grossInput.value = grossValue.toFixed(2);
        }

        // Uruchom funkcję obliczającą za każdym razem, gdy użytkownik coś wpisze
        netInput.addEventListener('input', calculateGross);
        vatRateInput.addEventListener('input', calculateGross);

    } else {
        console.warn("Nie znaleziono wszystkich pól formularza (totalNet, vatRate, totalGross) do obliczeń dynamicznych.");
    }
});