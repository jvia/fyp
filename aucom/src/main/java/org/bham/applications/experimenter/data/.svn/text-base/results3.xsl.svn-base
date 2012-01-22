<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:aucom="http://www.cor-lab.de/formats/ts/1.0">
<xsl:template match="aucom:results">
  <html>
<STYLE type="text/css">
  BODY {text-align: center}
 </STYLE>
  <body>
  <h2>Reults</h2>
    <xsl:for-each select="aucom:result">
  <table border="1" align="center" width="50%">
    <tr bgcolor="#9acd32">
	<td colspan="2" ><xsl:value-of select="position()"/></td>
    </tr>
    <tr>
      <td align="left">model</td>
	<td><xsl:value-of select="aucom:config/aucom:modelfile"/></td>
    </tr>
    <tr>
      <td align="left">optimization</td>
	<td><xsl:value-of select="aucom:config/aucom:optimizerfile"/></td>
    </tr>
    <tr >
      <td align="left">evaluation</td>
	<td><xsl:value-of select="aucom:config/aucom:evaluatefile"/></td>
    </tr>
    <tr >
      <td align="left">score mean total</td>
	<td><xsl:value-of select="aucom:measures/aucom:mscoretotal"/></td>
    </tr>
    <tr >
      <td align="left">score variance total</td>
	<td><xsl:value-of select="aucom:measures/aucom:vscoretotal"/></td>
    </tr>
<tr>
	<td align="left">score mean before fault</td>
	<td><xsl:value-of select="aucom:measures/aucom:mscorebefore"/></td>
    </tr>
<tr>
	<td align="left">score variance before fault</td>
	<td><xsl:value-of select="aucom:measures/aucom:vscorebefore"/></td>
    </tr>
<tr>
	<td align="left">score mean after fault</td>
	<td><xsl:value-of select="aucom:measures/aucom:mscoreafter"/></td>
    </tr>
<tr>
	<td align="left">score variance after fault</td>
	<td><xsl:value-of select="aucom:measures/aucom:vscoreafter"/></td>
    </tr>
<tr>
	<td align="left">fault detected</td>
	<td><xsl:value-of select="aucom:measures/aucom:fd"/></td>
    </tr>
<tr>
	<td align="left">fault tracking rate</td>
	<td><xsl:value-of select="aucom:measures/aucom:ftr"/></td>
    </tr>
<tr>
	<td align="left">false positive </td>
	<td><xsl:value-of select="aucom:measures/aucom:fp"/></td>
    </tr>
<tr>
	<td align="left">seriousness of the false positive </td>
	<td><xsl:value-of select="aucom:measures/aucom:falsepositiveseroiusness"/></td>
    </tr>
  </table>
    </xsl:for-each>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>
