
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
    <xsl:template match="/">
        ID_ART,CODE,GUID,USERNAME,NAME
        <xsl:for-each select="//article">
            <xsl:value-of select="concat(ID_ART,',',CODE,',',GUID,',',USERNAME,',',NAME,'&#13;')"/>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>