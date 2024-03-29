package Entidades;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class PrecoProdutoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "produto_id_produto")
    private int produtoIdProduto;
    @Basic(optional = false)
    @Column(name = "data_preco_produto")
    @Temporal(TemporalType.DATE)
    private Date dataPrecoProduto;

    public PrecoProdutoPK() {
    }

    public PrecoProdutoPK(int produtoIdProduto, Date dataPrecoProduto) {
        this.produtoIdProduto = produtoIdProduto;
        this.dataPrecoProduto = dataPrecoProduto;
    }

    public int getProdutoIdProduto() {
        return produtoIdProduto;
    }

    public void setProdutoIdProduto(int produtoIdProduto) {
        this.produtoIdProduto = produtoIdProduto;
    }

    public Date getDataPrecoProduto() {
        return dataPrecoProduto;
    }

    public void setDataPrecoProduto(Date dataPrecoProduto) {
        this.dataPrecoProduto = dataPrecoProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) produtoIdProduto;
        hash += (dataPrecoProduto != null ? dataPrecoProduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrecoProdutoPK)) {
            return false;
        }
        PrecoProdutoPK other = (PrecoProdutoPK) object;
        if (this.produtoIdProduto != other.produtoIdProduto) {
            return false;
        }
        if ((this.dataPrecoProduto == null && other.dataPrecoProduto != null) || (this.dataPrecoProduto != null && !this.dataPrecoProduto.equals(other.dataPrecoProduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PrecoProdutoPK[ produtoIdProduto=" + produtoIdProduto + ", dataPrecoProduto=" + dataPrecoProduto + " ]";
    }

// inicio - adicionado pelo gerador rdmsFromClasseEntidade
    public String getDataPrecoProdutoFormatado() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(getDataPrecoProduto());
    }

// fim -  adicionado pelo gerador rdmsFromClasseEntidade
}
