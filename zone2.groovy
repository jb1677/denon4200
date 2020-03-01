/**
 *  	Denon Network Receiver 
 *    	Based on Denon/Marantz receiver by Kristopher Kubicki
 *    	SmartThings driver to connect your Denon Network Receiver to SmartThings
 *		Tested with Denon AVR-X4200W

http://192.168.1.X/goform/formMainZone_MainZoneXmlStatus.xml
http://192.168.1.X/goform/formZone2_Zone2XmlStatus.xml
http://192.168.1.X/goform/formMainZone_MainZoneXml.xml
*/

metadata {
	definition (name: "Denon AVR", namespace: "Jason", author: "Jason Burch") {
		capability "Actuator"
		capability "Switch"
		capability "Polling"
		capability "Switch Level"
		capability "Music Player"
        
        attribute "input", "string" 
		
        command "on"
        command "off"
		command "mute"
		command "unmute"
		command "cblsat"
		command "dvd"
		command "blueray"
		command "game"
		command "aux1"
		command "aux2"
		command "mediaplayer"
		command "ipodusb"
		command "cd"
		command "tuner"
		command "network"
		command "tvaudio"
		command "bluetooth"
		command "phono"
	}
	
	
	preferences {
		input("destIp", "text", title: "IP", description: "The device IP")
		input("destPort", "number", title: "Port", description: "The port you wish to connect", defaultValue: 80)
        input("hasZone2", "bool", title: "AVR Have Second Zone?", defaultValue: false)
	}
	
	
	simulator {
		// TODO-: define status and reply messages here
	}
	
	tiles(scale: 2) {
        multiAttributeTile(name: "multiAVR", type:"mediaPlayer", width:6, height:4) {
            tileAttribute("device.status", key: "PRIMARY_CONTROL") {
                attributeState("paused", label:"Paused")
                attributeState("playing", label:"Playing")
            }
            tileAttribute("device.status", key: "MEDIA_STATUS") {
                attributeState "playing", label: '${name}', action:"switch.off", backgroundColor:"#00a0dc"
                attributeState "paused", label: '${name}', action:"switch.on", backgroundColor:"#ffffff"
            }
            tileAttribute ("device.level", key: "SLIDER_CONTROL") {
                attributeState("level", action:"setLevel")
            }
            tileAttribute ("device.mute", key: "MEDIA_MUTED") {
                attributeState("unmuted", action:"mute", nextState: "muted")
                attributeState("muted", action:"unmute", nextState: "unmuted")
            }
            tileAttribute("device.trackDescription", key: "MARQUEE") {
                attributeState("trackDescription", label:"${currentValue}", defaultState: true)
            }
        }
        standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: false) {
            state "on", label: '${name}', action: "switch.off", icon: "st.Electronics.electronics16", backgroundColor: "#53a7c0"
			state "off", label: '${name}', action: "switch.on", icon: "st.Electronics.electronics16", backgroundColor: "#ffffff"
		}
        standardTile("blank", "device.blank", width: 2, height: 2, decoration: "flat") {
			state "blank", label: "", backgroundColor: "#FFFFFF"
		}
        standardTile("poll", "device.poll", width: 2, height: 2, decoration: "flat") {
			state "poll", label: "", action: "polling.poll", icon: "st.secondary.refresh", backgroundColor: "#FFFFFF"
		}
		standardTile("cblsat", "device.cblsat", width: 2, height: 2, decoration: "flat"){
			state "hidden", label:'', icon:"", backgroundColor:"#ffffff", defaultState: true
            state "off", label: 'Tivo', action: "cblsat", icon:"st.Electronics.electronics3", backgroundColor: "#FFFFFF", nextState:"on"
			state "on", label: 'Tivo', action: "cblsat", icon:"st.Electronics.electronics3" , backgroundColor: "#53a7c0", nextState:"off"
		}
		standardTile("dvd", "device.dvd", width: 2, height: 2, decoration: "flat"){
			state "hidden", label:'', icon:"", backgroundColor:"#ffffff", defaultState: true
            state "off", label: 'DVD', action: "dvd", icon:"st.Electronics.electronics1", backgroundColor:"#FFFFFF",nextState:"on"
			state "on", label: 'DVD', action: "dvd", icon:"st.Electronics.electronics1", backgroundColor: "#53a7c0", nextState:"off"
		}
		standardTile("bluray", "device.bluray", width: 2, height: 2, decoration: "flat"){
			state "hidden", label:'', icon:"", backgroundColor:"#ffffff", defaultState: true
            state "off", label: 'PS3', action: "blueray", icon:"st.Electronics.electronics5", backgroundColor: "#FFFFFF",nextState:"on"
			state "on", label: 'PS3', action: "blueray", icon:"st.Electronics.electronics5", backgroundColor: "#53a7c0", nextState:"off"
		}
		standardTile("game", "device.game", width: 2, height: 2, decoration: "flat"){
			state "hidden", label:'', icon:"", backgroundColor:"#ffffff", defaultState: true
            state "off", label: 'PS4', action: "game", icon:"st.Electronics.electronics5", backgroundColor: "#FFFFFF",nextState:"on"
			state "on", label: 'PS4', action: "game", icon:"st.Electronics.electronics5", backgroundColor: "#53a7c0", nextState:"off"
		}
		standardTile("aux1", "device.aux1", width: 2, height: 2, decoration: "flat"){
			state "hidden", label:'', icon:"", backgroundColor:"#ffffff", defaultState: true
            state "off", label: 'Roku', action: "aux1", icon:"st.Electronics.electronics9", backgroundColor: "#FFFFFF",nextState:"on"
			state "on", label: 'Roku', action: "aux1", icon:"st.Electronics.electronics9", backgroundColor: "#53a7c0", nextState:"off"
		}
		standardTile("aux2", "device.aux2", width: 2, height: 2, decoration: "flat"){
			state "hidden", label:'', icon:"", backgroundColor:"#ffffff", defaultState: true
            state "off", label: 'Apple TV', action: "aux2", icon:"st.Electronics.electronics9", backgroundColor: "#FFFFFF",nextState:"on"
			state "on", label: 'Apple TV', action: "aux2", icon:"st.Electronics.electronics9", backgroundColor: "#53a7c0", nextState:"off"
		}
		standardTile("mediaplayer", "device.mediaplayer", width: 2, height: 2, decoration: "flat"){
			state "hidden", label:'', icon:"", backgroundColor:"#ffffff", defaultState: true
            state "off", label: 'PC', action: "mediaplayer", icon:"st.Electronics.electronics8", backgroundColor: "#FFFFFF",nextState:"on"
			state "on", label: 'PC', action: "mediaplayer", icon:"st.Electronics.electronics8", backgroundColor: "#53a7c0", nextState:"off"
		}
        standardTile("ipodusb", "device.ipodusb", width: 2, height: 2, decoration: "flat"){
			state "hidden", label:'', icon:"", backgroundColor:"#ffffff", defaultState: true
            state "off", label: 'iPod/USB', action: "ipodusb", icon:"st.Entertainment.entertainment4", backgroundColor: "#FFFFFF",nextState:"on"
			state "on", label: 'iPod/USB', action: "ipodusb", icon:"st.Entertainment.entertainment4", backgroundColor: "#53a7c0", nextState:"off"
		}
        standardTile("cd", "device.cd", width: 2, height: 2, decoration: "flat"){
			state "hidden", label:'', icon:"", backgroundColor:"#ffffff", defaultState: true
            state "off", label: 'Spotify', action: "cd", icon:"st.Electronics.electronics14", backgroundColor: "#FFFFFF",nextState:"on"
			state "on", label: 'Spotify', action: "cd", icon:"st.Electronics.electronics14", backgroundColor: "#53a7c0", nextState:"off"
		}
        standardTile("tuner", "device.tuner", width: 2, height: 2, decoration: "flat"){
			state "hidden", label:'', icon:"", backgroundColor:"#ffffff", defaultState: true
            state "off", label: 'Tuner', action: "tuner", icon:"st.Electronics.electronics10", backgroundColor: "#FFFFFF",nextState:"on"
			state "on", label: 'Tuner', action: "tuner", icon:"st.Electronics.electronics10", backgroundColor: "#53a7c0", nextState:"off"
		}
        standardTile("network", "device.network", width: 2, height: 2, decoration: "flat"){
			state "hidden", label:'', icon:"", backgroundColor:"#ffffff", defaultState: true
            state "off", label: 'Online Music', action: "network", icon:"st.Entertainment.entertainment2", backgroundColor: "#FFFFFF",nextState:"on"
			state "on", label: 'Online Music', action: "network", icon:"st.Entertainment.entertainment2", backgroundColor: "#53a7c0", nextState:"off"
		}
        standardTile("tvaudio", "device.tvaudio", width: 2, height: 2, decoration: "flat"){
			state "hidden", label:'', icon:"", backgroundColor:"#ffffff", defaultState: true
            state "off", label: 'TV Apps', action: "tvaudio", icon:"st.Electronics.electronics3", backgroundColor: "#FFFFFF",nextState:"on"
			state "on", label: 'TV Apps', action: "tvaudio", icon:"st.Electronics.electronics3", backgroundColor: "#53a7c0", nextState:"off"
		}
        standardTile("bluetooth", "device.bluetooth", width: 2, height: 2, decoration: "flat"){
			state "hidden", label:'', icon:"", backgroundColor:"#ffffff", defaultState: true
            state "off", label: 'Bluetooth', action: "bluetooth", icon:"st.Entertainment.entertainment15", backgroundColor: "#FFFFFF",nextState:"on"
			state "on", label: 'Bluetooth', action: "bluetooth", icon:"st.Entertainment.entertainment15", backgroundColor: "#53a7c0", nextState:"off"
		}
        standardTile("phono", "device.phono", width: 2, height: 2, decoration: "flat"){
			state "hidden", label:'', icon:"", backgroundColor:"#ffffff", defaultState: true
            state "off", label: 'Phono', action: "phono", icon:"st.Electronics.electronics4", backgroundColor: "#FFFFFF",nextState:"on"
			state "on", label: 'Phono', action: "phono", icon:"st.Electronics.electronics4", backgroundColor: "#53a7c0", nextState:"off"
		}
		standardTile("sound", "device.sound", width: 4, height: 2, decoration: "flat"){
			state "sMusic", label: '${currentValue}', action:"sMusic", icon:"st.Entertainment.entertainment3", backgroundColor: "#FFFFFF", nextState:"sMovie"
			state "sMovie", label: '${currentValue}', action:"sMovie", icon:"st.Entertainment.entertainment9", backgroundColor: "#FFFFFF", nextState:"sGame"
			state "sGame", label: '${currentValue}', action:"sGame", icon:"st.Electronics.electronics6", backgroundColor: "#FFFFFF", nextState:"sPure"
			state "sPure", label: '${currentValue}', action:"sPure", icon:"st.Entertainment.entertainment15", backgroundColor: "#FFFFFF", nextState:"sMusic"
		}
		main "multiAVR"
		details(["multiAVR", "switch", "poll", "cd", "cblsat", "bluray", "game", "aux1", "aux2", "mediaplayer", "ipodusb", "tuner", "network", "tvaudio", "bluetooth"])
	}
}

def updated() {
	//log.debug("Updated called.")
	//sendEvent(name: "mplay", value: "Apple TV", isStateChange: true);
}
def parse(String description) {
	def result = []
    
    //log.debug "Parsing '${description}'"
 	def map = stringToMap(description)
    if (!map.body || map.body == "DQo=") {
    	return
    }
	def body = new String(map.body.decodeBase64())
	def statusrsp = new XmlSlurper().parseText(body)
    
    // Store event updates
    def eventUpdates = [:]
    
    //CURRENT INPUT
    def currentInput = statusrsp.InputFuncSelect.value.text()
    currentInput = currentInput.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
    currentInput = translateInput(currentInput)
    
    // Remove hidden inputs
    eventUpdates = removeHiddenInputs(statusrsp, currentInput, eventUpdates)
    
    //POWER STATUS	
    def power = statusrsp.Power.value.text()
	if(power == "ON") { 
    	eventUpdates.put("status", "playing")
        eventUpdates.put("switch", "on")
    }
    if(power != "" && power != "ON") {  
    	eventUpdates.put("status", "paused")
        eventUpdates.put("switch", "off")
	}
	//VOLUME STATUS    
    def muteLevel = statusrsp.Mute.value.text()
    if(muteLevel == "on") { 
    	eventUpdates.put("mute", "muted")
	}
    if(muteLevel != "" && muteLevel != "on") {
	    eventUpdates.put("mute", "unmuted")
    }
    if(statusrsp.MasterVolume.value.text()) { 
    	def rawValue = statusrsp.MasterVolume.value
        if (rawValue == "--") {
        	rawValue = 0
        }
        def int volLevel = (int) rawValue.toFloat() ?: -40.0
        volLevel = (volLevel + 80)
        	//log.debug "Adjusted volume is ${volLevel}"
        def int curLevel = 36
        try {
        	curLevel = device.currentValue("level")
        	//log.debug "Current volume is ${curLevel}"
        } catch(NumberFormatException nfe) { 
        	curLevel = 36
        }
        if(curLevel != volLevel) {
    		eventUpdates.put("level", volLevel)
        }
    }
    
    //result << sendEvent(name: "tv", value: "off")
    
    /*
    def inputSurr = statusrsp.selectSurround.value.text()
    		result << sendEvent(name: "sound", value: inputSurr)
	        log.debug "Current Surround is: ${inputSurr}"  
    def inputZone = statusrsp.RenameZone.value.text()
    		//sendEvent(name: "sound", value: inputSurr)
	        log.debug "Current Active Zone is: ${inputZone}"
    */
    
    def zone = statusrsp.Zone.value.text().toLowerCase().trim()
    //log.debug "zone: '${zone}'"
    
    if (zone == "main zone") {
        def previousInput = device.currentValue("input")
        //log.debug "Previous Input is: ${previousInput} is ${device.currentValue(previousInput)}, Current Input is: ${currentInput}"
        if (previousInput != currentInput && device.currentValue(previousInput) == "on") {
            eventUpdates.put("${previousInput}", "off")
            eventUpdates.put("${currentInput}", "on")
            eventUpdates.put("input", currentInput)
            eventUpdates.put("trackDescription", currentInput)
        } else if (previousInput == currentInput && device.currentValue(currentInput) == "off") {
            eventUpdates.put("${currentInput}", "on")
        }
        //log.debug "eventUpdateKeys: ${eventUpdates.keySet()}"
        eventUpdates.each { key, value ->
            //log.debug "sendEvent ${zone} = ${key}: ${value}"
            result << sendEvent(name: "${key}", value: "${value}")
        }
        return result
    } else if (hasZone2) {
        def childDevice = null
        try {
            childDevices.each {
                try{
                    //log.debug "1-Looking for child with deviceNetworkID = ${it.deviceNetworkId}"
                    if (it.deviceNetworkId == "${device.deviceNetworkId}-${zone}") {
                        childDevice = it
                        //log.debug "Found a match!!!"
                    }
                }
                catch (e) {
                    //log.debug e
                }
            }

            //If a child should exist, but doesn't yet, automatically add it!
            if (childDevice == null) {
                //log.debug "No child found - Auto Add it!"
                createChildDevice(zone)
                //find child again, since it should now exist!
                childDevices.each {
                    try {
                        //log.debug "2-Looking for child with deviceNetworkID = ${it.deviceNetworkId}"
                        if (it.deviceNetworkId == "${device.deviceNetworkId}-${zone}") {
                            childDevice = it
                            //log.debug "Found a match!!!"
                        }
                    } catch (e) {
                        //log.debug e
                    }
                }
            }

            if (childDevice != null) {
                def previousInput = childDevice.currentValue("input")
                //log.debug "Previous Input is: ${previousInput} is ${childDevice.currentValue(previousInput)}, Current Input is: ${currentInput}"
                if (previousInput != currentInput && childDevice.currentValue(previousInput) == "on") {
                    eventUpdates.put("${previousInput}", "off")
                    eventUpdates.put("${currentInput}", "on")
                    eventUpdates.put("input", currentInput)
                    eventUpdates.put("trackDescription", currentInput)
                } else if (previousInput == currentInput && device.currentValue(currentInput) == "off") {
                    eventUpdates.put("${currentInput}", "on")
                }
                //log.debug "parse() found child device ${childDevice.deviceNetworkId}.  Generating Events: ${eventUpdates.keySet()}"
                //childDevice.sendEvent(name: "dvd", value: "off")
                return childDevice.generateEvent(eventUpdates)
            }
        } catch (e) {
            log.error "Error in parse() routine, error = ${e}"
        }
    }
}
//TILE ACTIONS
def setLevel(val) {
    sendEvent(name: "mute", value: "unmuted")     
    sendEvent(name: "level", value: val)
    def int scaledVal = val - 80
    def cmd = "cmd0=PutMasterVolumeSet%2F" + scaledVal
    request(cmd)
}
def on() {
    sendEvent(name: "status", value: "playing")
    sendEvent(name: "switch", value: "on")
    def cmd = "cmd0=PutZone_OnOff%2FON"
    request(cmd)
}
def off() { 
    sendEvent(name: "status", value: "paused")
    sendEvent(name: "switch", value: "off")
    def cmd = "cmd0=PutZone_OnOff%2FOFF"
    request(cmd)
}
def mute() { 
    sendEvent(name: "mute", value: "muted")
    def cmd = "cmd0=PutVolumeMute%2FON"
    request(cmd)
}
def unmute() { 
    sendEvent(name: "mute", value: "unmuted")
    def cmd = "cmd0=PutVolumeMute%2FOFF"
    request(cmd)
}
def toggleMute(){
    if (device.currentValue("mute") == "muted") {
    	unmute()
    } else {
    	mute()
    }
}
def cblsat() {
    updateInputs("cblsat")
    def cmd = "SAT/CBL"
    request(cmd)
}
def dvd() {
    updateInputs("dvd")
    def cmd = "DVD"
    request(cmd)
}
def blueray() {
    updateInputs("bluray")
    def cmd = "BD"
    request(cmd)
}
def game() {
    updateInputs("game")
    def cmd = "GAME"
    request(cmd)
}
def aux1() {
    updateInputs("aux1")
    def cmd = "AUX1"
    request(cmd)
}
def aux2() {
    updateInputs("aux2")
    def cmd = "AUX2"
    request(cmd)
}
def mediaplayer() {
    updateInputs("mediaplayer")
    def cmd = "MPLAY"
    request(cmd)
}
def ipodusb() {
    updateInputs("ipodusb")
    def cmd = "USB/IPOD"
    request(cmd)
}
def cd() {
    updateInputs("cd")
    def cmd = "CD"
    request(cmd)
}
def tuner() {
    updateInputs("tuner")
    def cmd = "TUNER"
    request(cmd)
}
def network() {
    updateInputs("network")
    def cmd = "NET"
    request(cmd)
}
def tvaudio() {
    updateInputs("tvaudio")
    def cmd = "TV"
    request(cmd)
}
def bluetooth() {
    updateInputs("bluetooth")
    def cmd = "BT"
    request(cmd)
}
def phono() {
    updateInputs("phono")
    def cmd = "PHONO"
    request(cmd)
}


//QUICK MODES
def q1() {
    def cmd = "1"
    log.debug "Setting quick input to '${cmd}'"
    syncQTiles(cmd)
    request("cmd0=PutUserMode%2FQuick%2F"+cmd)
}
def q2() { 
    def cmd = "2"
    log.debug "Setting quick input to '${cmd}'"
    syncQTiles(cmd)
    request("cmd0=PutSurroundMode%2F"+cmd)
}
def q3() {
    def cmd = "3"
    log.debug "Setting quick input to '${cmd}'"
    syncQTiles(cmd)
    request("cmd0=PutSurroundMode%2F"+cmd)
}
def q4() {
    def cmd = "4"
    log.debug "Setting quick input to '${cmd}'"
    syncQTiles(cmd)
    request("cmd0=PutZone_InputFunction%2F"+cmd)
}    
def poll() { 
    log.debug "Polling requested"
    refresh("all")
}
def syncQTiles(cmd){
    if (cmd == "1") sendEvent(name: "q1", value: "on")	 
    else sendEvent(name: "q1", value: "off")						
    if (cmd == "2") sendEvent(name: "q2", value: "on")	 
    else sendEvent(name: "q2", value: "off")						
    if (cmd == "3") sendEvent(name: "q3", value: "on")	 
    else sendEvent(name: "q3", value: "off")						
    if (cmd == "4") sendEvent(name: "q4", value: "on")	 
    else sendEvent(name: "q4", value: "off")						
}

//Other functions
def refresh(zone) {
    def hosthex = convertIPtoHex(destIp)
    def porthex = convertPortToHex(destPort)
    device.deviceNetworkId = "$hosthex:$porthex"
    
    if (zone == "" || zone == null) {
    	zone = "all"
    } else if (zone instanceof String == false) {
    	zone = zone.name
    }
    
    if (zone == "all" || zone == "main") {
        sendHubCommand(new physicalgraph.device.HubAction(
            'method': 'GET',
            'path': "/goform/formMainZone_MainZoneXmlStatus.xml",
            'headers': [ HOST: "$destIp:$destPort" ] 
        ))
    }
    
    if (hasZone2 && (zone == "all" || zone == "zone2")) {
        sendHubCommand(new physicalgraph.device.HubAction(
            'method': 'GET',
            'path': "/goform/formZone2_Zone2XmlStatus.xml",
            'headers': [ HOST: "$destIp:$destPort" ] 
        ))
    }
}
def request(cmd) { 
    //log.debug "Setting input to '${cmd}'"
    def body = "cmd0=PutZone_InputFunction%2F" + cmd
    if (cmd.indexOf("cmd0=") > -1) {
    	body = cmd
    }
    def path = "/MainZone/index.put.asp"
    
    def hosthex = convertIPtoHex(destIp)
    def porthex = convertPortToHex(destPort)
    device.deviceNetworkId = "$hosthex:$porthex" 
    
    sendHubCommand(new physicalgraph.device.HubAction(
        'method': 'POST',
        'path': path,
        'body': body,
        'headers': [ HOST: "$destIp:$destPort" ]
    ))
    
    def zoneName = (body.endsWith("ZONE2")) ? "zone2" : "main"
    //log.debug "request zoneName: ${zoneName}"
    runIn(5, "refresh", [data: [name: zoneName]])
}
def removeHiddenInputs(statusResponse, currentInput, eventUpdates) {
    def index = 0
    
    statusResponse.InputFuncList.value.each { inputName ->     
        def inputHidden = statusResponse.SourceDelete.value[index].toString()
        def inputNameLower = inputName.toString().toLowerCase().replaceAll("[^A-Za-z0-9]", "");
        def inputValue = (inputHidden == "DEL") ? "hidden" : "off"
        
        // Update multi bottom track description with input rename value
        if (inputNameLower == currentInput) {
        	def inputRename = statusResponse.RenameSource.value[index].toString()
            eventUpdates.put("trackDescription", inputRename)
        } else {
        	if (device.currentValue("${inputNameLower}") != inputValue) {
            	//log.debug "removeHiddenInputs = ${inputNameLower} = ${inputValue}"
                eventUpdates.put("${inputNameLower}", inputValue)
            }
        }
        index += 1
    }
    
    return eventUpdates
}
def updateInputs(currentInput) {
	def result = []
    result << sendEvent(name: "${currentInput}", value: "on")
    def previousInput = device.currentValue("input")
    result << sendEvent(name: "${previousInput}", value: "off")
    result << sendEvent(name: "input", value: currentInput)
    result << sendEvent(name: "trackDescription", value: currentInput)
    
    return result
}

private String translateInput(inputName) {
	def answer = ""
	switch(inputName) {
		case "satcbl":
			answer = "cblsat"
			break
		case "dvd":
			answer = "dvd"
			break
		case "bd":
			answer = "bluray"
			break
		case "game":
			answer = "game"
			break
		case "aux1":
			answer = "aux1"
			break
		case "aux2":
			answer = "aux2"
			break
		case "mplay":
			answer = "mediaplayer"
			break
		case "usbipod":
			answer = "ipodusb"
			break
		case "cd":
			answer = "cd"
			break
		case "tuner":
			answer = "tuner"
			break
		case "net":
			answer = "network"
			break
		case "tv":
			answer = "tvaudio"
			break
		case "bt":
			answer = "bluetooth"
			break
		case "phono":
			answer = "phono"
			break
	}
	return answer
}
private String convertIPtoHex(ipAddress) { 
    String hex = ipAddress.tokenize( '.' ).collect {  String.format( '%02X', it.toInteger() ) }.join()
    return hex
}
private String convertPortToHex(port) {
    String hexport = port.toString().format( '%04X', port.toInteger() )
    return hexport
}

private void createChildDevice(deviceNumber) {
	def childDeviceHandlerName = "Denon AVR Zone 2"
    def childDeviceLabel = "${device.displayName} Zone 2"
    log.trace "createChildDevice:  Creating Child Device '${childDeviceLabel} (${device.deviceNetworkId}-${deviceNumber})'"

    try {
        
        if (deviceHandlerName != "") {
            addChildDevice(childDeviceHandlerName, "${device.deviceNetworkId}-${deviceNumber}", null,
                           [completedSetup: true, label: "${childDeviceLabel}", 
                            isComponent: false, componentName: "${device.deviceNetworkId}${deviceNumber}", componentLabel: "${device.deviceNetworkId} ${deviceNumber}"])
        }   
    } catch (e) {
        log.error "Child device creation failed with error = ${e}"
        state.alertMessage = "Child device creation failed. Please make sure that the '${deviceHandlerName}' is installed and published."
    }
}
/*
def getInputMap(statusResponse) {
    def inputMap = [:]
    def index = 0
    
    statusResponse.InputFuncList.value.each { inputName ->
        //log.debug "InputFuncList = ${inputName} = ${index}"
        def indInputMap = [:]
        indInputMap.put("rename", statusResponse.RenameSource.value[index].toString())
        
        def inputHidden = statusResponse.SourceDelete.value[index].toString()
        indInputMap.put("hidden", inputHidden)
        
        //log.debug "InputFuncList = ${inputName} = ${index} ${indInputMap}"
        
        
        
        inputMap.put(inputName.toString(), indInputMap)
        index += 1
    }
    log.debug "inputMap = ${inputMap}"
    log.debug "inputMap Keys: ${inputMap.keySet()}"
    //log.debug "inputMap['DVD']: ${inputMap['DVD']}"
    log.debug "inputMap.DVD: ${inputMap.DVD}"
    log.debug "inputMap.DVD.hidden: ${inputMap.DVD.hidden}"
    log.debug "=============== End getInputMap function ==============="
}
*/
