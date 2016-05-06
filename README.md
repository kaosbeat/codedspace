##goals	
- easyly sync two computers using overtone and quil
- an external midiclock is used at first for simplicity
- the master pc runs supercollider
- the master pc runs the repl
- the slave PC connects to the repl and reads data like sequences from it
- the slave PC 

##tech


## environment - dependencies
emacs live
overtone
quil
leipzig


##setup

### clone repo

###start midiout app
you can use the supplied PD-patch (midiclock.pd), but this should be opusmodus, and on the same rhythm as the rest of the instruments.
Do this befor overtone is booted so the midi device is recognized upon booting overtone

###start repl
`lein repl :host 0.0.0.0 :port 7888`

the `:host 0.0.0.0` is for allowing external connections
the `:port` is for fixing the port

###


