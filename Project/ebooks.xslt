<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
    <h2>Colecção de Ebooks Bertrand</h2>
    <xsl:for-each select="list/ebook">
      <div style="display: inline; width: 500px; height: 280px; float: left; word-wrap: break-word; background-color: #ffc37d; margin: 20px;">
        <table border="1">
          <td>
            <tr><xsl:value-of select="titulo"/></tr>
            <td>
              <img>
                <xsl:attribute name="src">
                  <xsl:value-of select="capaURL"/>
                </xsl:attribute>
              </img>
            </td>
            <td>
              <tr>ISBN: <xsl:value-of select="@ISBN"/></tr>
              <tr>Autor: <xsl:value-of select="autor"/></tr>
              <tr>Classificação: <xsl:value-of select="classificacao"/></tr>
              <tr>Formato: <xsl:value-of select="formato"/></tr>
              <xsl:for-each select="edicao">
                <tr>Editor: <xsl:value-of select="editor"/></tr>
                <tr>Ano de Edição: <xsl:value-of select="anoEdicao"/></tr>
              </xsl:for-each>
              <tr>Pontos Bertrand: <xsl:value-of select="pontosBertrand"/></tr>
              <tr>Preço: <xsl:value-of select="preco"/><xsl:value-of select="preco/@moeda"/></tr>
            </td>
          </td>
        </table>
      </div>
    </xsl:for-each>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>

