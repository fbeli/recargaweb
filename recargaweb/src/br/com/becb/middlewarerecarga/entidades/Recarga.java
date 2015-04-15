	package br.com.becb.middlewarerecarga.entidades;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "recarga")
public class Recarga {
	
	
	
	@Id
	@Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, length=6)
	private long id;
	private StatusRecarga statusRecarga;
	@Size(max=9) @NotNull @NotEmpty
	private String fone;
	
	@Size(max=2) @NotNull @NotEmpty
	private String ddd;	
	private Date dataDeSolicitacao;
	private Date dataDeconfirmacao;
	private String mensagem;
	private String vencimento;	

	private String codOnline;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "produto_id", nullable=false)
	private Produto produto;
	
	private String idTerminal;
	private double valor;
	private double compra;
	private String usuarioLocal;
	private String pagamento;
	
	private String nsu;
	
	private  StatusRecargaServer statusRecargaServer;
	
	/** Para Recargas de Pin */
	private String reenvio;
	
	private String face;
	
	private String pin;
	private String pago;	
	private String lote;
	private String dataRV;
	private String serie;
	private String possuiBoleto;
	private String 	uf_terminal;
	
	
	
	
	/**
	 * Campos abaixo apenas para validaçÕ de en
	 */
	/*
	 * Indica o código do produto no sistema da RV.
	 	*Pode ser diferente do código do produto solicitado, em caso de produtos AGRUPADOS (por exemplo, cliente pode solicitar
		*uma recarga para o produto “VIVO R$8” do DDD 021, e receber no retorno então o código do produto “VIVO RJ R$8”)
	 */
	private String idProdutoAjustado;
	
	public Recarga() {
		this.dataDeSolicitacao = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public StatusRecarga getStatusRecarga() {
		return statusRecarga;
	}

	public void setStatusRecarga(StatusRecarga statusRecarga) {
		this.statusRecarga = statusRecarga;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public Date getDataDeSolicitacao() {
		return dataDeSolicitacao;
	}

	public void setDataDeSolicitacao(Date dataDeSolicitacao) {
		this.dataDeSolicitacao = dataDeSolicitacao;
	}

	public Date getDataDeconfirmacao() {
		return dataDeconfirmacao;
	}

	public void setDataDeconfirmacao(Date dataDeconfirmacao) {
		this.dataDeconfirmacao = dataDeconfirmacao;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public String getCodOnline() {
		return codOnline;
	}

	public void setCodOnline(String codOnline) {
		this.codOnline = codOnline;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getIdTerminal() {
		return idTerminal;
	}

	public void setIdTerminal(String idTerminal) {
		this.idTerminal = idTerminal;
	}

	

	

	public String getUsuarioLocal() {
		return usuarioLocal;
	}

	public void setUsuarioLocal(String usuarioLocal) {
		this.usuarioLocal = usuarioLocal;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getCompra() {
		return compra;
	}

	public void setCompra(double compra) {
		this.compra = compra;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public String getNsu() {
		return nsu;
	}

	public void setNsu(String nsu) {
		this.nsu = nsu;
	}

	public String getIdProdutoAjustado() {
		return idProdutoAjustado;
	}

	public void setIdProdutoAjustado(String idProdutoAjustado) {
		this.idProdutoAjustado = idProdutoAjustado;
	}

	public StatusRecargaServer getStatusRecargaServer() {
		return statusRecargaServer;
	}

	public void setStatusRecargaServer(StatusRecargaServer statusRecargaServer) {
		this.statusRecargaServer = statusRecargaServer;
	}

	public String getReenvio() {
		return reenvio;
	}

	public void setReenvio(String reenvio) {
		this.reenvio = reenvio;
	}

	

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	
	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPago() {
		return pago;
	}

	public void setPago(String pago) {
		this.pago = pago;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getDataRV() {
		return dataRV;
	}

	public void setDataRV(String dataRV) {
		this.dataRV = dataRV;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getPossuiBoleto() {
		return possuiBoleto;
	}

	public void setPossuiBoleto(String possuiBoleto) {
		this.possuiBoleto = possuiBoleto;
	}

	public String getUf_terminal() {
		return uf_terminal;
	}

	public void setUf_terminal(String uf_terminal) {
		this.uf_terminal = uf_terminal;
	}
	
}
