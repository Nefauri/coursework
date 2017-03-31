// fields and constructor
Vector4 = function(x,y,z,w)
{
	this.x = ( x === undefined ) ? 0 : x;
	this.y = ( y === undefined ) ? 0 : y;
	this.z = ( z === undefined ) ? 0 : z;
	this.w = ( w === undefined ) ? 1 : w;
	
	this.floatArray = new Float32Array(4);
}

// instance methods
Vector4.prototype = 
{
	set: function ( x, y, z, w ) 
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		return this;
	},

	copy: function ( v ) 
	{
		this.set( v.x, v.y, v.z, v.w );
	},

	clone: function () 
	{
		var w = new Vector4();
		w.copy( this );
		return w;
	},

	toArray: function() 
	{
		this.floatArray[ 0 ] = this.x; 
		this.floatArray[ 1 ] = this.y; 
		this.floatArray[ 2 ] = this.z; 
		this.floatArray[ 3 ] = this.w;
		return this.floatArray;
	},
	
	toString: function()
	{
		return "[" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + "]";
	},
	
	add: function ( v ) 
	{
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
		this.w += v.w;
		return this;
	},

	sub: function ( v ) 
	{
		this.x -= v.x;
		this.y -= v.y;
		this.z -= v.z;
		this.w -= v.w;
		return this;
	},

	multiplyScalar: function( s ) 
	{
		this.x *= s;
		this.y *= s;
		this.z *= s;
		this.w *= s;
		return this;
	},

	divideScalar: function( s ) 
	{
		return this.multiplyScalar( 1/s );
	},

	negate: function() 
	{
		return this.multiplyScalar( -1 );
	},
	
	dot: function( v )
	{
		return this.x * v.x + this.y * v.y + this.z * v.z + this.w * v.w;
	},

	length: function() 
	{
		return Math.sqrt( this.dot( this ) );
	},

	normalize: function() 
	{
		return this.divideScalar( this.length() );
	},

	setLength: function ( len ) 
	{
		return this.normalize().multiplyScalar( len );
	},
	
	cross: function( v )
	{
		return new Vector4(this.y * v.z - this.z * v.y, 
						  -this.x * v.z + this.z * v.x, 
						   this.x * v.y - this.y * v.x,
						   0.0);
	}
	
};