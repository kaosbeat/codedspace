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
for now, `lein repl` check port number use that later when making connection from emacs
<!--`lein repl :host 0.0.0.0 :port 21337`

the `:host 0.0.0.0` is for allowing external connections
the `:port` is for fixing the port

-->
the bossig.core file is autamagically loaded (because we specified it in the project.clj file)


###open emacs live
more specifically first load the bossig.core namespace (codedspace/src/bossig/core.clj) and connect it to the repl
`C-c M-c` using the correct host (localhost if connecting to your own machine) and port, <!--should be 21337-->


