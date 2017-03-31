
// constructor
Matrix4 = function ( n11, n12, n13, n14, n21, n22, n23, n24, n31, n32, n33, n34, n41, n42, n43, n44 ) 
{
	this.n11 = ( n11 !== undefined ) ? n11 : 1; this.n12 = n12 || 0; this.n13 = n13 || 0; this.n14 = n14 || 0;
	this.n21 = n21 || 0; this.n22 = ( n22 !== undefined ) ? n22 : 1; this.n23 = n23 || 0; this.n24 = n24 || 0;
	this.n31 = n31 || 0; this.n32 = n32 || 0; this.n33 = ( n33 !== undefined ) ? n33 : 1; this.n34 = n34 || 0;
	this.n41 = n41 || 0; this.n42 = n42 || 0; this.n43 = n43 || 0; this.n44 = ( n44 !== undefined ) ? n44 : 1;
	
	this.floatArray = new Float32Array(16);
};

Matrix4.prototype = 
{
	set: function ( n11, n12, n13, n14, n21, n22, n23, n24, n31, n32, n33, n34, n41, n42, n43, n44 ) 
	{
		this.n11 = n11; this.n12 = n12; this.n13 = n13; this.n14 = n14;
		this.n21 = n21; this.n22 = n22; this.n23 = n23; this.n24 = n24;
		this.n31 = n31; this.n32 = n32; this.n33 = n33; this.n34 = n34;
		this.n41 = n41; this.n42 = n42; this.n43 = n43; this.n44 = n44;

		return this;
	},

	copy: function ( m ) 
	{
		this.set(
			m.n11, m.n12, m.n13, m.n14,
			m.n21, m.n22, m.n23, m.n24,
			m.n31, m.n32, m.n33, m.n34,
			m.n41, m.n42, m.n43, m.n44
		);
		return this;
	},
	
	clone: function () 
	{
		var m = new Matrix4();
		m.copy( this );
		return m;
	},
	
	toArray: function () 
	{
		this.floatArray[ 0 ] = this.n11; this.floatArray[ 1 ] = this.n21; this.floatArray[ 2 ] = this.n31; this.floatArray[ 3 ] = this.n41;
		this.floatArray[ 4 ] = this.n12; this.floatArray[ 5 ] = this.n22; this.floatArray[ 6 ] = this.n32; this.floatArray[ 7 ] = this.n42;
		this.floatArray[ 8 ]  = this.n13; this.floatArray[ 9 ]  = this.n23; this.floatArray[ 10 ] = this.n33; this.floatArray[ 11 ] = this.n43;
		this.floatArray[ 12 ] = this.n14; this.floatArray[ 13 ] = this.n24; this.floatArray[ 14 ] = this.n34; this.floatArray[ 15 ] = this.n44;
		return this.floatArray;
	},
	
	// sets this Matrix to be the product of two others...
	multiply: function ( b ) 
	{
		var a11 = this.n11, a12 = this.n12, a13 = this.n13, a14 = this.n14,
			a21 = this.n21, a22 = this.n22, a23 = this.n23, a24 = this.n24,
			a31 = this.n31, a32 = this.n32, a33 = this.n33, a34 = this.n34,
			a41 = this.n41, a42 = this.n42, a43 = this.n43, a44 = this.n44,

			b11 = b.n11, b12 = b.n12, b13 = b.n13, b14 = b.n14,
			b21 = b.n21, b22 = b.n22, b23 = b.n23, b24 = b.n24,
			b31 = b.n31, b32 = b.n32, b33 = b.n33, b34 = b.n34,
			b41 = b.n41, b42 = b.n42, b43 = b.n43, b44 = b.n44;

		this.n11 = a11 * b11 + a12 * b21 + a13 * b31 + a14 * b41;
		this.n12 = a11 * b12 + a12 * b22 + a13 * b32 + a14 * b42;
		this.n13 = a11 * b13 + a12 * b23 + a13 * b33 + a14 * b43;
		this.n14 = a11 * b14 + a12 * b24 + a13 * b34 + a14 * b44;

		this.n21 = a21 * b11 + a22 * b21 + a23 * b31 + a24 * b41;
		this.n22 = a21 * b12 + a22 * b22 + a23 * b32 + a24 * b42;
		this.n23 = a21 * b13 + a22 * b23 + a23 * b33 + a24 * b43;
		this.n24 = a21 * b14 + a22 * b24 + a23 * b34 + a24 * b44;

		this.n31 = a31 * b11 + a32 * b21 + a33 * b31 + a34 * b41;
		this.n32 = a31 * b12 + a32 * b22 + a33 * b32 + a34 * b42;
		this.n33 = a31 * b13 + a32 * b23 + a33 * b33 + a34 * b43;
		this.n34 = a31 * b14 + a32 * b24 + a33 * b34 + a34 * b44;

		this.n41 = a41 * b11 + a42 * b21 + a43 * b31 + a44 * b41;
		this.n42 = a41 * b12 + a42 * b22 + a43 * b32 + a44 * b42;
		this.n43 = a41 * b13 + a42 * b23 + a43 * b33 + a44 * b43;
		this.n44 = a41 * b14 + a42 * b24 + a43 * b34 + a44 * b44;

		return this;
	},
	
	multiplyScalar: function ( s ) 
	{
		this.n11 *= s; this.n12 *= s; this.n13 *= s; this.n14 *= s;
		this.n21 *= s; this.n22 *= s; this.n23 *= s; this.n24 *= s;
		this.n31 *= s; this.n32 *= s; this.n33 *= s; this.n34 *= s;
		this.n41 *= s; this.n42 *= s; this.n43 *= s; this.n44 *= s;
		return this;
	},
	
	multiplyVector: function ( v ) 
	{
		var vx = v.x, vy = v.y, vz = v.z, vw = v.w;

		v.x = this.n11 * vx + this.n12 * vy + this.n13 * vz + this.n14 * vw;
		v.y = this.n21 * vx + this.n22 * vy + this.n23 * vz + this.n24 * vw;
		v.z = this.n31 * vx + this.n32 * vy + this.n33 * vz + this.n34 * vw;
		v.w = this.n41 * vx + this.n42 * vy + this.n43 * vz + this.n44 * vw;

		return v;
	},
	
	determinant: function () 
	{
		var n11 = this.n11, n12 = this.n12, n13 = this.n13, n14 = this.n14,
		n21 = this.n21, n22 = this.n22, n23 = this.n23, n24 = this.n24,
		n31 = this.n31, n32 = this.n32, n33 = this.n33, n34 = this.n34,
		n41 = this.n41, n42 = this.n42, n43 = this.n43, n44 = this.n44;

		return (
			n14 * n23 * n32 * n41-
			n13 * n24 * n32 * n41-
			n14 * n22 * n33 * n41+
			n12 * n24 * n33 * n41+

			n13 * n22 * n34 * n41-
			n12 * n23 * n34 * n41-
			n14 * n23 * n31 * n42+
			n13 * n24 * n31 * n42+

			n14 * n21 * n33 * n42-
			n11 * n24 * n33 * n42-
			n13 * n21 * n34 * n42+
			n11 * n23 * n34 * n42+

			n14 * n22 * n31 * n43-
			n12 * n24 * n31 * n43-
			n14 * n21 * n32 * n43+
			n11 * n24 * n32 * n43+

			n12 * n21 * n34 * n43-
			n11 * n22 * n34 * n43-
			n13 * n22 * n31 * n44+
			n12 * n23 * n31 * n44+

			n13 * n21 * n32 * n44-
			n11 * n23 * n32 * n44-
			n12 * n21 * n33 * n44+
			n11 * n22 * n33 * n44
		);
	},

	setIdentity: function() 
	{
		this.set(
			1, 0, 0, 0,
			0, 1, 0, 0,
			0, 0, 1, 0,
			0, 0, 0, 1
		);
		return this;
	},
	
	setTranslation: function( x, y, z ) 
	{
		this.set(
			1, 0, 0, x,
			0, 1, 0, y,
			0, 0, 1, z,
			0, 0, 0, 1
		);
		return this;
	},

	setScale: function ( x, y, z ) 
	{
		this.set(
			x, 0, 0, 0,
			0, y, 0, 0,
			0, 0, z, 0,
			0, 0, 0, 1
		);
		return this;
	},

	setRotationX: function ( theta ) 
	{
		var c = Math.cos( theta ), s = Math.sin( theta );
		this.set(
			1, 0,  0, 0,
			0, c, -s, 0,
			0, s,  c, 0,
			0, 0,  0, 1
		);
		return this;
	},

	setRotationY: function( theta ) 
	{
		var c = Math.cos( theta ), s = Math.sin( theta );
		this.set(
			 c, 0, s, 0,
			 0, 1, 0, 0,
			-s, 0, c, 0,
			 0, 0, 0, 1
		);
		return this;
	},

	setRotationZ: function( theta ) 
	{
		var c = Math.cos( theta ), s = Math.sin( theta );
		this.set(
			c, -s, 0, 0,
			s,  c, 0, 0,
			0,  0, 1, 0,
			0,  0, 0, 1
		);
		return this;
	},

	setRotationAxis: function( axis, angle ) 
	{
		// Based on http://www.gamedev.net/reference/articles/article1199.asp

		var c = Math.cos( angle ),
		s = Math.sin( angle ),
		t = 1 - c,
		x = axis.x, y = axis.y, z = axis.z,
		tx = t * x, ty = t * y;

		this.set(
		 	tx * x + c, tx * y - s * z, tx * z + s * y, 0,
			tx * y + s * z, ty * y + c, ty * z - s * x, 0,
			tx * z - s * y, ty * z + s * x, t * z * z + c, 0,
			0, 0, 0, 1
		);
		 return this;
	},

	inverse: function () 
	{
		// based on http://www.euclideanspace.com/maths/algebra/matrix/functions/inverse/fourD/index.htm

		var d = this.determinant();
		
		var n11 = this.n11, n12 = this.n12, n13 = this.n13, n14 = this.n14,
		n21 = this.n21, n22 = this.n22, n23 = this.n23, n24 = this.n24,
		n31 = this.n31, n32 = this.n32, n33 = this.n33, n34 = this.n34,
		n41 = this.n41, n42 = this.n42, n43 = this.n43, n44 = this.n44;

		this.n11 = n23*n34*n42 - n24*n33*n42 + n24*n32*n43 - n22*n34*n43 - n23*n32*n44 + n22*n33*n44;
		this.n12 = n14*n33*n42 - n13*n34*n42 - n14*n32*n43 + n12*n34*n43 + n13*n32*n44 - n12*n33*n44;
		this.n13 = n13*n24*n42 - n14*n23*n42 + n14*n22*n43 - n12*n24*n43 - n13*n22*n44 + n12*n23*n44;
		this.n14 = n14*n23*n32 - n13*n24*n32 - n14*n22*n33 + n12*n24*n33 + n13*n22*n34 - n12*n23*n34;
		this.n21 = n24*n33*n41 - n23*n34*n41 - n24*n31*n43 + n21*n34*n43 + n23*n31*n44 - n21*n33*n44;
		this.n22 = n13*n34*n41 - n14*n33*n41 + n14*n31*n43 - n11*n34*n43 - n13*n31*n44 + n11*n33*n44;
		this.n23 = n14*n23*n41 - n13*n24*n41 - n14*n21*n43 + n11*n24*n43 + n13*n21*n44 - n11*n23*n44;
		this.n24 = n13*n24*n31 - n14*n23*n31 + n14*n21*n33 - n11*n24*n33 - n13*n21*n34 + n11*n23*n34;
		this.n31 = n22*n34*n41 - n24*n32*n41 + n24*n31*n42 - n21*n34*n42 - n22*n31*n44 + n21*n32*n44;
		this.n32 = n14*n32*n41 - n12*n34*n41 - n14*n31*n42 + n11*n34*n42 + n12*n31*n44 - n11*n32*n44;
		this.n33 = n13*n24*n41 - n14*n22*n41 + n14*n21*n42 - n11*n24*n42 - n12*n21*n44 + n11*n22*n44;
		this.n34 = n14*n22*n31 - n12*n24*n31 - n14*n21*n32 + n11*n24*n32 + n12*n21*n34 - n11*n22*n34;
		this.n41 = n23*n32*n41 - n22*n33*n41 - n23*n31*n42 + n21*n33*n42 + n22*n31*n43 - n21*n32*n43;
		this.n42 = n12*n33*n41 - n13*n32*n41 + n13*n31*n42 - n11*n33*n42 - n12*n31*n43 + n11*n32*n43;
		this.n43 = n13*n22*n41 - n12*n23*n41 - n13*n21*n42 + n11*n23*n42 + n12*n21*n43 - n11*n22*n43;
		this.n44 = n12*n23*n31 - n13*n22*n31 + n13*n21*n32 - n11*n23*n32 - n12*n21*n33 + n11*n22*n33;
		this.multiplyScalar( 1 / d );

		return this;
	},

	setLookAt: function( cameraPosition, cameraDirection, cameraUp )
	{
		var direction = cameraDirection.clone().normalize(); 
		var up 		  = cameraUp.clone().normalize(); 
		var side      = direction.clone().cross( up );

		// change-of-basis matrix
		this.set(
			side.x, up.x, -direction.x, 0,
			side.y, up.y, -direction.y, 0,
			side.z, up.z, -direction.z, 0,
			0, 0, 0, 1
		);
		
		this.multiply( new Matrix4().setTranslation(-cameraPosition.x, -cameraPosition.y, -cameraPosition.z) );
		return this;
	},
	
	setFrustum: function( left, right, bottom, top, near, far ) 
	{
		var m, x, y, a, b, c, d;

		m = new Matrix4();

		x = 2 * near / ( right - left );
		y = 2 * near / ( top - bottom );

		a = ( right + left ) / ( right - left );
		b = ( top + bottom ) / ( top - bottom );
		c = - ( far + near ) / ( far - near );
		d = - 2 * far * near / ( far - near );

		m.n11 = x;  m.n12 = 0;  m.n13 = a;   m.n14 = 0;
		m.n21 = 0;  m.n22 = y;  m.n23 = b;   m.n24 = 0;
		m.n31 = 0;  m.n32 = 0;  m.n33 = c;   m.n34 = d;
		m.n41 = 0;  m.n42 = 0;  m.n43 = - 1; m.n44 = 0;
		
		this.copy(m);
		return this;
	},

	setPerspective: function ( fov, aspect, near, far ) 
	{
		var ymax, ymin, xmin, xmax;

		ymax = near * Math.tan( fov * Math.PI / 360 );
		ymin = - ymax;
		xmin = ymin * aspect;
		xmax = ymax * aspect;

		return this.setFrustum( xmin, xmax, ymin, ymax, near, far );
	}
};


