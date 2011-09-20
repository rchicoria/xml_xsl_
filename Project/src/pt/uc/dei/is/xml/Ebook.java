package pt.uc.dei.is.xml;

public class Ebook {

	private String titulo;
	private String autor;
	private String capaURL;
	private String classificacao;
	private String formato;
	private int anoEdicao;
	private String editor;
	private String ISBN;
	private float pontosBertrand;
	private float preco;
	
	/**
	 * Cria um novo Ebook e recolhe imediatamente todos os seus dados
	 * com o auxílio do HTML disponibilizado
	 * @param html
	 */
	public Ebook(String html)
	{
		this.titulo = RegEx.getTitulo(html);
		this.autor = RegEx.getAutor(html);
		this.capaURL = RegEx.getCapaURL(html);
		this.classificacao = RegEx.getClassificacao(html);
		this.formato = RegEx.getFormato(html);
		this.anoEdicao = RegEx.getAnoEdicao(html);
		this.editor = RegEx.getEditor(html);
		this.ISBN = RegEx.getISBN(html);
		this.pontosBertrand = RegEx.getPontosBertrand(html);
		this.preco = RegEx.getPreco(html);
	}
	
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}
	/**
	 * @param autor the autor to set
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}
	/**
	 * @return the capaURL
	 */
	public String getCapaURL() {
		return capaURL;
	}
	/**
	 * @param capaURL the capaURL to set
	 */
	public void setCapaURL(String capaURL) {
		this.capaURL = capaURL;
	}
	/**
	 * @return the classificacao
	 */
	public String getClassificacao() {
		return classificacao;
	}
	/**
	 * @param classificacao the classificacao to set
	 */
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	/**
	 * @return the formato
	 */
	public String getFormato() {
		return formato;
	}
	/**
	 * @param formato the formato to set
	 */
	public void setFormato(String formato) {
		this.formato = formato;
	}
	/**
	 * @return the anoEdicao
	 */
	public int getAnoEdicao() {
		return anoEdicao;
	}
	/**
	 * @param anoEdicao the anoEdicao to set
	 */
	public void setAnoEdicao(int anoEdicao) {
		this.anoEdicao = anoEdicao;
	}
	/**
	 * @return the editor
	 */
	public String getEditor() {
		return editor;
	}
	/**
	 * @param editor the editor to set
	 */
	public void setEditor(String editor) {
		this.editor = editor;
	}
	/**
	 * @return the iSBN
	 */
	public String getISBN() {
		return ISBN;
	}
	/**
	 * @param iSBN the iSBN to set
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	/**
	 * @return the pontosBertrand
	 */
	public float getPontosBertrand() {
		return pontosBertrand;
	}
	/**
	 * @param pontosBertrand the pontosBertrand to set
	 */
	public void setPontosBertrand(float pontosBertrand) {
		this.pontosBertrand = pontosBertrand;
	}
	/**
	 * @return the preco
	 */
	public float getPreco() {
		return preco;
	}
	/**
	 * @param preco the preco to set
	 */
	public void setPreco(float preco) {
		this.preco = preco;
	}
	
}
