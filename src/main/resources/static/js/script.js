document.addEventListener('DOMContentLoaded', (event) => {
    addListenerAnimation(event)
    addListenerTransition(event)
});


function addListenerAnimation(event) {
    const cells = document.querySelectorAll('table td');
    cells.forEach(cell => {
        cell.style.transition = 'transform 0.5s ease';
        cell.addEventListener('mouseover', () => {
            cell.style.transform = 'scale(1.1)';
        });
        cell.addEventListener('mouseout', () => {
            cell.style.transform = 'scale(1)';
        });
    });}

function addListenerTransition(event) {
    const rows = document.querySelectorAll('table tr');
    rows.forEach(row => {
        row.addEventListener('click', () => {
            const guid = row.getAttribute('data-guid');
            if (guid) {
                const url = `/bankruptcy/${guid}`;
                window.location.href = url;
            }
        });
    });
}