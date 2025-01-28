document.getElementById('noteForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const title = document.getElementById('title').value;
    const group = document.getElementById('group').value;
    const content = document.getElementById('content').value;

    console.log('Nota creada:', { title, group, content });
    alert('Nota guardada correctamente.');
    this.reset();
});