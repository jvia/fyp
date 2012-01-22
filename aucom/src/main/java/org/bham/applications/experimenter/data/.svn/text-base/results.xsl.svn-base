<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:aucom="http://www.cor-lab.de/formats/ts/1.0">
<xsl:template match="aucom:results">
  <html>
  <body>
  <h2>Reults</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th>Nr.</th>
      <th>model</th>
      <th>optimization</th>
      <th>evaluation</th>
      <th>False Positive Rate total</th>
      <th>Mean Score total</th>
      <th>Score Variance total</th>
    </tr>
    <xsl:for-each select="aucom:result">
    <tr>
	<td><xsl:value-of select="position()"/></td>
	<td><xsl:value-of select="aucom:config/aucom:modelfile"/></td>
	<td><xsl:value-of select="aucom:config/aucom:optimizerfile"/></td>
	<td><xsl:value-of select="aucom:config/aucom:evaluatefile"/></td>
	<td><xsl:value-of select="aucom:config/aucom:evaluatefile"/></td>
	<td><xsl:value-of select="aucom:config/aucom:evaluatefile"/></td>
	<td><xsl:value-of select="aucom:config/aucom:evaluatefile"/></td>
    </tr>
    </xsl:for-each>
  </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>
