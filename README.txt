MatheMagic Online

Author: Skyler Voran
Creation date: 2/14/2022
Last update date: 2/22/2022

Commands:
	LOGIN:
		This command allows the user to try to login to the server. 
		If the user does not log in, then they cannot call any other
		commands. There are only 4 preset username and password 
		combinations.
			The presets are: 
				root root22
				john john22
				sally sally22
				qiang qiang22
	SOLVE:
		This command lets the user get the circumference and area of
		a circle OR the area and permineter of a rectangle or square.
		Calling SOLVE -c 4, where 4 is the radius, returns the area 
		and circumference of a circle. Calling -r 2 4, where 2 and 4
		are length of 2 sides, returns the area and perimeter of the
		rectangle. Calling -r 2, where 2 is a side, assumes all sides
		are 2, and returns the are and perimeter of a square.
	LIST:
		This commands lets the user see the previously called SOVLE
		commands, and their results. If there has not been any called
		SOLVE commands, it will notify the user. If the user is logged 
		into the root account, and calls LIST -all, this will return
		the previously called commands from all accounts, and in a
		ordelry fashion
	LOGOUT:
		This command will close the client, and continue to run the
		server. So, a new client can be ran, and access the server 
		after one client logs out!
	SHUTDOWN:
		This command will close the server, and also close the client.

How to build and run this program:
	1. Run the server
	2. Run the client
	3. In the client, type "LOGIN" followed by the username and password
	4. If correct, you will gain access to the server commands
	5. Call as many SOLVE commands as youd like (format given above)
	6. Optional - Switch users by calling "LOGIN" again
	7. Call more SOLVE commands
	8. Call LIST command (format given above)
	9. Call LOGOUT or SHUTDOWN depending on your next steps!

Known bugs or assumptions:
	1. We are assumming the user will eneter numbers when calling
	   the SOLVE command.
	2. When calling LOGOUT, an error message prints on the server side,
	   but the new client can still connect and use the commands!
	3. We are assuming the user knows the correct username and passwords,
	   which I have given above.

Ouputs of commands:
	LOGIN:
		Login failure:
			LOGIN test test22
			C:	LOGIN test test22
			S:	FAILURE: Please provide correct username and password. Try again.
		Login success:
			LOGIN root root22
			C:	LOGIN root root22
			S:	SUCCESS 
	SOLVE:
		Circle:
			SOLVE -c 4
			C:	SOLVE -c 4
			S:	Circle’s circumference is 25.13 and area is 50.27
		Circle no sides:
			SOLVE -c
			C:	SOLVE -c
			S:	301 message format error
		Rectangle:
			SOLVE -r 2 4
			C:	SOLVE -r 2 4
			S:	Rectangle’s perimeter is 12.0 and area is 8.0
		Square:
			SOLVE -r 2
			C:	SOLVE -r 2
			S:	Rectangle’s perimeter is 8.0 and area is 4.0
		Rectangle no sides:
			SOLVE -r
			C:	SOLVE -r
			S:	301 message format error
	LIST:
		LIST of root:
			LIST
			C:	LIST
			S:	root
					Radius 4: Circle’s circumference is 25.13 and area is 50.27
					301 message format error
					301 message format error
					Sides 2 4: Rectangle’s perimeter is 12.0 and area is 8.0
					Sides 2 2: Rectangle’s perimeter is 8.0 and area is 4.0
					301 message format error
		List of all:
			LIST -all
			C:	LIST -all
			S:	root
					Radius 4: Circle’s circumference is 25.13 and area is 50.27
					301 message format error
					301 message format error
					Sides 2 4: Rectangle’s perimeter is 12.0 and area is 8.0
					Sides 2 2: Rectangle’s perimeter is 8.0 and area is 4.0
					301 message format error
				john
					No interactions yet
				sally
					No interactions yet
				qiang
					No interactions yet
	LOGOUT:
		LOGOUT
		C:	LOGOUT
		S:	200 OK
	SHUTDOWN:
		SHUTDOWN
		C:	SHUTDOWN
		S:	200 OK

Output of Server:

run:
c:	LOGIN test test22
s:	FAILURE: Please provide correct username and password. Try again.
c:	LOGIN root root22
s:	SUCCESS
c:	SOLVE -c 4
s:	Circle’s circumference is 25.13 and area is 50.27
c:	SOLVE -c 
s:	301 message format error
c:	SOLVE -c
s:	301 message format error
c:	SOLVE -r 2 4
s:	Rectangle’s perimeter is 12.0 and area is 8.0
c:	SOLVE -r 2
s:	Rectangle’s perimeter is 8.0 and area is 4.0
c:	SOLVE -r
s:	301 message format error
c:	LIST
s:	root
		Radius 4: Circle’s circumference is 25.13 and area is 50.27
		301 message format error
		301 message format error
		Sides 2 4: Rectangle’s perimeter is 12.0 and area is 8.0
		Sides 2 2: Rectangle’s perimeter is 8.0 and area is 4.0
		301 message format error
c:	LIST -all
s:	root
		Radius 4: Circle’s circumference is 25.13 and area is 50.27
		301 message format error
		301 message format error
		Sides 2 4: Rectangle’s perimeter is 12.0 and area is 8.0
		Sides 2 2: Rectangle’s perimeter is 8.0 and area is 4.0
		301 message format error
	john
		No interactions yet
	sally
		No interactions yet
	qiang
		No interactions yet
c:	LOGOUT
s:	200 OK
c:	SHUTDOWN
s:	200 OK
BUILD SUCCESSFUL (total time: 12 minutes 59 seconds)	