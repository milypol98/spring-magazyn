// tasks.js - wspólny JS dla listy, edycji i szczegółów zadań (backend-driven filters)
(() => {
    const $ = (sel, root = document) => root.querySelector(sel);
    const $$ = (sel, root = document) => Array.from(root.querySelectorAll(sel));
    const debounce = (fn, wait = 400) => { let t; return (...args) => { clearTimeout(t); t = setTimeout(() => fn.apply(null, args), wait); }; };

    function markInvalid(input, invalid, msg) {
        if (!input) return;
        input.classList.toggle('is-invalid', !!invalid);
        let hint = input.nextElementSibling && input.nextElementSibling.classList?.contains('field-hint')
            ? input.nextElementSibling : null;
        if (invalid && msg) {
            if (!hint) {
                hint = document.createElement('div');
                hint.className = 'field-hint';
                input.insertAdjacentElement('afterend', hint);
            }
            hint.textContent = msg;
        } else if (hint) {
            hint.remove();
        }
    }

    // LISTA
    function initList() {
        const filtersForm = $('.filters-form');
        const table = $('.list-container table');
        if (!filtersForm) return;

        const submitClosestForm = (el) => { const f = el.closest('form'); if (f) f.submit(); };

        // auto-submit przy zmianach pól select/date/size
        $$('.filters-form select[name="size"], .filters-form input[type="date"], .filters-form select[name="status"], .filters-form select[name="userId"]').forEach((ctrl) => {
            ctrl.addEventListener('change', () => submitClosestForm(ctrl));
        });

        // input q z debounce, Enter i Escape
        const q = $('.filters-form input[name="q"]');
        if (q) {
            const run = debounce(() => submitClosestForm(q), 500);
            q.addEventListener('input', run);
            q.addEventListener('keydown', (e) => {
                if (e.key === 'Escape') { q.value = ''; run(); }
                if (e.key === 'Enter') { e.preventDefault(); submitClosestForm(q); }
            });
        }

        // blokada klików w disabled paginacji
        $$('.pagination .page-btn.disabled').forEach((a) => a.addEventListener('click', (e) => e.preventDefault()));

        // confirm dla usuwania
        $$('.list-container form[method="post"]').forEach((f) => {
            if (!f.hasAttribute('onsubmit') && f.action.includes('/delete')) {
                f.addEventListener('submit', (e) => { if (!confirm('Usunąć to zadanie?')) e.preventDefault(); });
            }
        });
    }

    // EDYCJA
    function initEdit() {
        const form = $('form[action$="/tasks/save"]');
        if (!form) return;

        const name = $('input[name="name"]', form);
        const status = $('select[name="status"]', form);
        const dateFrom = $('input[name="dateFrom"]', form);
        const dateTo = $('input[name="dateTo"]', form);

        [name, status, dateFrom, dateTo].forEach(inp => {
            if (inp) inp.addEventListener('input', () => markInvalid(inp, false));
            if (inp) inp.addEventListener('change', () => markInvalid(inp, false));
        });

        form.addEventListener('submit', (e) => {
            let ok = true;

            if (!name || name.value.trim() === '') { markInvalid(name, true, 'Podaj nazwę zadania.'); ok = false; }
            if (status && (status.value == null || status.value === '')) { markInvalid(status, true, 'Wybierz status.'); ok = false; }

            const df = dateFrom && dateFrom.value ? new Date(dateFrom.value) : null;
            const dt = dateTo && dateTo.value ? new Date(dateTo.value) : null;
            if (df && dt && df.getTime() > dt.getTime()) {
                markInvalid(dateFrom, true, 'Data „od” nie może być po dacie „do”.');
                markInvalid(dateTo, true, 'Data „od” nie może być po dacie „do”.');
                ok = false;
            }

            if (!ok) {
                e.preventDefault();
                const firstInvalid = $('.is-invalid', form);
                if (firstInvalid) firstInvalid.scrollIntoView({ behavior: 'smooth', block: 'center' });
            }
        });
    }

    // SZCZEGÓŁY
    function initDetails() {
        const delForm = $('form[action*="/tasks/delete/"]');
        if (delForm && !delForm.hasAttribute('onsubmit')) {
            delForm.addEventListener('submit', (e) => {
                if (!confirm('Czy na pewno usunąć to zadanie?')) e.preventDefault();
            });
        }
    }

    document.addEventListener('DOMContentLoaded', () => {
        initList();
        initEdit();
        initDetails();
    });
})();