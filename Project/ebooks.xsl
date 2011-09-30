<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:b="http://bertrand.pt">

<xsl:template match="/">
  <html>
  <head>
  	<title>Colecção de Ebooks Bertrand</title>
  	<style>
  		td {
  			font-size: 16px;
  			vertical-align: top;
  			font-family: Arial;
  		}
  		body {
  			text-align: center;
  			background: #fff7c2;
  		}
  		div {
  			text-align: left;
  		}
  		h2, h3 {
  			font-family: Serif;
  			color: #390000;
  		}
  	</style>
  </head>
  <body>
  	<div style="margin: 0 auto 0 auto; text-align: center">
  	<h2>Colecção de Ebooks Bertrand</h2>
    <xsl:for-each select="b:list/b:ebook">
      <div style="display: inline-block; width: 550px; height: 360px; background-color: #ffc37d; border: 1px solid #cc904a; margin: 20px; padding: 10px; border-radius: 10px; box-shadow: 5px 2px 10px #999;">
        <table width="100%">
          <tr>
          	<td width="180" align="center">
              <img style="border: 1px solid #cc904a; margin-top: 20px" >
                <xsl:attribute name="src">
                  <xsl:value-of select="b:capaURL"/>
                </xsl:attribute>
              </img>
            </td>
            <td style="line-height: 150%">
            <h3><xsl:value-of select="b:titulo"/></h3>
            <table width="100%">
            	<tr>
	          		<td width="40%"><b>ISBN</b></td>
	          		<td><xsl:value-of select="@ISBN"/></td>
	          	</tr>

	          	<tr>
	          		<td><b>Autor</b></td>
	          		<td><xsl:value-of select="b:autor"/></td>
	          	</tr>
        	<xsl:for-each select="b:tema">
			    <tr>
			    	<td><b>Categoria</b></td>
			    	<td><xsl:value-of select="b:categoria"/></td>
			    </tr>
			    <tr>
			        <td><b>Subcategoria</b></td>
			        <td><xsl:value-of select="b:subcategoria"/></td>
			    </tr>
			    </xsl:for-each>
	          	<tr>
	          		<td><b>Formato</b></td>
	          		<td><xsl:value-of select="b:formato"/></td>
	          	</tr>
			    <xsl:for-each select="b:edicao">
			    <tr>
			    	<td><b>Editor</b></td>
			    	<td><xsl:value-of select="b:editor"/></td>
			    </tr>
			    <tr>
			        <td><b>Ano de Edição</b></td>
			        <td><xsl:value-of select="b:anoEdicao"/></td>
			    </tr>
			    </xsl:for-each>
	          	<tr>
	          		<td><b>Pontos Bertrand</b></td>
	          		<td><xsl:value-of select="b:pontosBertrand"/></td>
	          	</tr>
	          	<tr>
	          		<td><b>Preço</b></td>
	          		<td><xsl:value-of select="b:preco"/>&#160;<xsl:value-of select="b:preco/@moeda"/></td>
	          	</tr>
	        </table>
            </td>
          </tr>
        </table>
      </div>
    </xsl:for-each>
    </div>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>

