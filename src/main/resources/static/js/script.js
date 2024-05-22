function generatePDF() {
    // Cria uma nova instância do jsPDF
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    // Define as colunas e as linhas da tabela
    const columns = ["ID", "Name", "Email"];
    const rows = [
        [1, "John Doe", "john.doe@example.com"],
        [2, "Jane Smith", "jane.smith@example.com"],
        [3, "Billy Joe", "billy.joe@example.com"]
    ];

    // Usa o plugin autoTable para adicionar a tabela ao PDF
    doc.autoTable({
        head: [columns],
        body: rows
    });

    // Salva o PDF
    doc.save("table.pdf");
}
