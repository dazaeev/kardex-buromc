/*
 * @autor Nazario Dazaeev Gonzalez Herrera
 * Controller 
 * Utilerias MIME
 */

function getExtention(name) {
	var extention = '';
	//
	if (name.includes('audio/aac')) {
		extention = '.aac';
	}
	if (name.includes('application/x-abiword')) {
		extention = '.abw';
	}
	if (name.includes('application/octet-stream')) {
		extention = '.arc';
	}
	if (name.includes('video/x-msvideo')) {
		extention = '.avi';
	}
	if (name.includes('application/vnd.amazon.ebook')) {
		extention = '.azw';
	}
	if (name.includes('application/octet-stream')) {
		extention = '.bin';
	}
	if (name.includes('application/x-bzip')) {
		extention = '.bz';
	}
	if (name.includes('application/x-bzip2')) {
		extention = '.bz2';
	}
	if (name.includes('application/x-csh')) {
		extention = '.csh';
	}
	if (name.includes('text/css')) {
		extention = '.css';
	}
	if (name.includes('text/csv')) {
		extention = '.csv';
	}
	if (name.includes('application/msword')) {
		extention = '.doc';
	}
	
	if (name.includes('application/vnd.openxmlformats-officedocument.wordprocessingml.document')) {
		extention = '.docx';
	}
	if (name.includes('application/vnd.openxmlformats-officedocument.presentationml.presentation')) {
		extention = '.pptx';
	}
	if (name.includes('application/vnd.openxmlformats-officedocument.spreadsheetml.sheet')) {
		extention = '.xlsx';
	}
	
	if (name.includes('application/epub+zip')) {
		extention = '.epub';
	}
	if (name.includes('image/gif')) {
		extention = '.gif';
	}
	if (name.includes('text/html')) {
		extention = '.html';
	}
	if (name.includes('image/x-icon')) {
		extention = '.ico';
	}
	if (name.includes('text/calendar')) {
		extention = '.ics';
	}
	if (name.includes('application/java-archive')) {
		extention = '.jar';
	}
	if (name.includes('image/jpeg')) {
		extention = '.jpeg';
	}
	if (name.includes('application/javascript')) {
		extention = '.js';
	}
	if (name.includes('application/json')) {
		extention = '.json';
	}
	if (name.includes('audio/midi')) {
		extention = '.mid';
	}
	if (name.includes('video/mpeg')) {
		extention = '.mpeg';
	}
	if (name.includes('application/vnd.apple.installer+xml')) {
		extention = '.mpkg';
	}
	if (name.includes('application/vnd.oasis.opendocument.presentation')) {
		extention = '.odp';
	}
	if (name.includes('application/vnd.oasis.opendocument.spreadsheet')) {
		extention = '.ods';
	}
	if (name.includes('application/vnd.oasis.opendocument.text')) {
		extention = '.odt';
	}
	if (name.includes('audio/ogg')) {
		extention = '.oga';
	}
	if (name.includes('video/ogg')) {
		extention = '.ogv';
	}
	if (name.includes('application/ogg')) {
		extention = '.ogx';
	}
	if (name.includes('application/pdf')) {
		extention = '.pdf';
	}
	if (name.includes('application/vnd.ms-powerpoint')) {
		extention = '.ppt';
	}
	if (name.includes('application/x-rar-compressed')) {
		extention = '.rar';
	}
	if (name.includes('application/rtf')) {
		extention = '.rtf';
	}
	if (name.includes('application/x-sh')) {
		extention = '.sh';
	}
	if (name.includes('image/svg+xml')) {
		extention = '.svg';
	}
	if (name.includes('application/x-shockwave-flash')) {
		extention = '.swf';
	}
	if (name.includes('application/x-tar')) {
		extention = '.tar';
	}
	if (name.includes('image/tiff')) {
		extention = '.tif';
	}
	if (name.includes('font/ttf')) {
		extention = '.ttf';
	}
	if (name.includes('application/vnd.visio')) {
		extention = '.vsd';
	}
	if (name.includes('audio/x-wav')) {
		extention = '.wav';
	}
	if (name.includes('audio/webm')) {
		extention = '.weba';
	}
	if (name.includes('video/webm')) {
		extention = '.webm';
	}
	if (name.includes('image/webp')) {
		extention = '.webp';
	}
	if (name.includes('font/woff')) {
		extention = '.woff';
	}
	if (name.includes('font/woff2')) {
		extention = '.woff2';
	}
	if (name.includes('application/xhtml+xml')) {
		extention = '.xhtml';
	}
	if (name.includes('application/vnd.ms-excel')) {
		extention = '.xls';
	}
	if (name.includes('application/xml')) {
		extention = '.xml';
	}
	if (name.includes('application/vnd.mozilla.xul+xml')) {
		extention = '.xul';
	}
	if (name.includes('application/zip')) {
		extention = '.zip';
	}
	
	if (name.includes('application/x-zip-compressed')) {
		extention = '.zip';
	}
	if (name.includes('application/application/x-gzip')) {
		extention = '.gz';
	}
	
	if (name.includes('video/3gpp')) {
		extention = '.3gp';
	}
	if (name.includes('video/3gpp2')) {
		extention = '.3g2';
	}
	if (name.includes('application/x-7z-compressed')) {
		extention = '.7z';
	}
	//
	return extention;
}