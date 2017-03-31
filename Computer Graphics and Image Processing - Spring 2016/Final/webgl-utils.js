function setupWebGL( canvas )
{
	var context = null;
	context = canvas.getContext("webgl");
	
	if (!context)
		{ alert("Error: setupWebGL failed; no rendering context."); }

	return context;
}

function initShaders( gl, vertexShaderID, fragmentShaderID )
{
    var vertexElement = document.getElementById( vertexShaderID );
	
    if ( !vertexElement ) 
		{ alert( "Error: loading " + vertexShaderID ); }
		
	var vertexShader = gl.createShader( gl.VERTEX_SHADER );
	
    gl.shaderSource( vertexShader, vertexElement.text );
	
    gl.compileShader( vertexShader );
	
    if ( !gl.getShaderParameter(vertexShader, gl.COMPILE_STATUS) ) 
		{ alert( "Error: vertex shader compiler: " + gl.getShaderInfoLog( vertexShader ) ); }

    var fragmentElement = document.getElementById( fragmentShaderID );
	
    if ( !fragmentElement ) 
		{ alert( "Error: loading " + fragmentShaderID ); }
		
	var fragmentShader = gl.createShader( gl.FRAGMENT_SHADER );
	
    gl.shaderSource( fragmentShader, fragmentElement.text );
	
    gl.compileShader( fragmentShader );
	
    if ( !gl.getShaderParameter(fragmentShader, gl.COMPILE_STATUS) ) 
		{ alert( "Error: fragment shader compiler: " + gl.getShaderInfoLog( fragmentShader ) ); }

    var program = gl.createProgram();
    gl.attachShader( program, vertexShader );
    gl.attachShader( program, fragmentShader );
	
    gl.linkProgram( program );
	
    if ( !gl.getProgramParameter(program, gl.LINK_STATUS) ) 
		{ alert( "Error: program linking: " + gl.getProgramInfoLog(program) ); }

    return program;
}
