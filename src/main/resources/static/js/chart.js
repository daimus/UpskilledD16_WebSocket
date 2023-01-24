const ctx = document.getElementById('vote-result-chart');

var pc = new Chart(ctx, {
    type: 'pie',
    data: {
        labels: ['Mansur Iman', 'Agus Arif', 'Buana Melati'],
        datasets: [{
            label: '# of Votes',
            data: [0, 0, 0],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});