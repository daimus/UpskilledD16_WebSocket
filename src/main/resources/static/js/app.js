let stompClient = null;
let idNumber = null;

function handleSignIn(){
    idNumber = document.getElementById('id-number').value;
    toggleUserState()
    toggleVoteButton();
}

function handleSignOut(){
    idNumber = null;
    document.getElementById('id-number').value = '';
    document.getElementById('alert-thank-you').classList.add('d-none')
    toggleUserState();
}

function toggleUserState(){
    document.getElementById('id-number').classList.toggle('d-none')
    document.getElementById('signin-button').classList.toggle('d-none')
    document.getElementById('signout-button').classList.toggle('d-none')
}

function toggleVoteButton(){
    document.querySelectorAll('.vote-button').forEach(el => {
        el.classList.toggle('d-none')
    })
}

function connect(){
    let socket = new SockJS('/vote-ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/i/vote-update', function (message) {
            const voteUpdate = JSON.parse(message.body);
            parseVoteUpdate(voteUpdate);
        });
        stompClient.subscribe('/i/vote-summary', function (message) {
            const voteSummary = JSON.parse(message.body);
            parseVoteSummary(voteSummary);
        });
    });
}

function parseVoteUpdate(voteUpdate){
    const voteUpdateListElement = document.getElementById('vote-update-list');
    voteUpdateListElement.innerHTML = `<li><strong>${String(voteUpdate.identityNumber).charAt(0)}${String(voteUpdate.identityNumber).charAt(1)}**********</strong> has voted</li>`+ voteUpdateListElement.innerHTML
}

function parseVoteSummary(voteSummary){
    document.getElementById('candidate1-vote-count').innerText = voteSummary.candidate1;
    document.getElementById('candidate2-vote-count').innerText = voteSummary.candidate2;
    document.getElementById('candidate3-vote-count').innerText = voteSummary.candidate3;
    document.getElementById('total-vote-count').innerText = voteSummary.candidate1 + voteSummary.candidate2 + voteSummary.candidate3;

    pc.data.datasets[0].data = [voteSummary.candidate1, voteSummary.candidate2, voteSummary.candidate3];
    pc.update();
}

function vote(candidateId){
    const voteData = {
        identityNumber: idNumber,
        candidateId: candidateId
    }
    stompClient.send("/o/vote", {}, JSON.stringify(voteData));
    toggleVoteButton()
    document.getElementById('alert-thank-you').classList.toggle('d-none')
}

