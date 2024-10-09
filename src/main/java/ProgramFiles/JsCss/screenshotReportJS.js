function onLoadFunction () {
	alert ("onLoadFunction");
}//onLoadFunction

const openEnlargedImg = imgSrc => {
	window.open(imgSrc,'Image','width=\"100%\",height=\"auto\",resizable=1');
};