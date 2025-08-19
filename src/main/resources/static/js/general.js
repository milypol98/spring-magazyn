document.addEventListener('DOMContentLoaded', () => {

    // ===== Sekcja 1: Logika uniwersalnego wyszukiwania i sumowania =====

    let filterTimeoutId;

    function filterTable(inputElement, tableElement) {
        clearTimeout(filterTimeoutId);
        filterTimeoutId = setTimeout(() => {
            const query = (inputElement.value || '').trim().toLowerCase();
            const rows = Array.from(tableElement.tBodies[0].querySelectorAll('tr'));

            rows.forEach(row => {
                // Pomiń wiersz stopki, jeśli jest w tbody (dla bezpieczeństwa)
                if (row.closest('tfoot')) return;
                const text = (row.textContent || '').toLowerCase();
                row.style.display = !query || text.includes(query) ? '' : 'none';
            });

            // Po przefiltrowaniu, uruchom ponowne przeliczenie sum
            triggerSummation(inputElement, tableElement);
        }, 150); // Opóźnienie 150ms
    }

    /**
     * KLUCZOWA POPRAWKA: Sumuje wartości tylko z WIDOCZNYCH wierszy.
     */
    function calculateColumnSum(tableElement, columnIndex, resultElementId) {
        let sum = 0;
        const rows = Array.from(tableElement.tBodies[0].rows);
        const resultElement = document.getElementById(resultElementId);

        if (!resultElement) {
            console.error("Element na sumę o ID nie został znaleziony:", resultElementId);
            return;
        }

        for (const row of rows) {
            // Sprawdź, czy wiersz jest widoczny
            if (row.style.display !== 'none') {
                const cell = row.cells[columnIndex];
                if (cell) {
                    const value = parseFloat(cell.innerText.replace(',', '.'));
                    if (!isNaN(value)) {
                        sum += value;
                    }
                }
            }
        }
        resultElement.innerText = sum.toFixed(2).replace('.', ',');
    }

    function triggerSummation(inputElement, tableElement) {
        const columnsToSumConfig = inputElement.dataset.sumColumns;
        if (columnsToSumConfig) {
            const sumTasks = columnsToSumConfig.split(',');
            sumTasks.forEach(task => {
                const [columnIndex, sumElementId] = task.split(':');
                calculateColumnSum(tableElement, parseInt(columnIndex, 10), sumElementId);
            });
        }
    }

    // Inicjalizacja dla wszystkich uniwersalnych wyszukiwarek na stronie
    const allSearchInputs = document.querySelectorAll('.table-search[data-target-table]');
    allSearchInputs.forEach(input => {
        const tableSelector = input.dataset.targetTable;
        const targetTable = document.querySelector(tableSelector);

        if (targetTable) {
            // 1. Oblicz sumy na starcie
            triggerSummation(input, targetTable);
            // 2. Ustaw nasłuchiwanie na zmiany w polu input
            input.addEventListener('input', () => filterTable(input, targetTable));
        }
    });


});