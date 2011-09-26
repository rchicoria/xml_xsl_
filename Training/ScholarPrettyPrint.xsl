<?xml version="1.0" encoding="UTF-8" ?>
<html xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xsl:version="1.0">
	<head>
		<title> Investigadores/Artigos </title>
	</head>
	<body>
		<xsl:for-each select="//author">
			Nome: <b> <xsl:value-of select="."/> </b> <br/>
		</xsl:for-each>
	</body>
</html>
