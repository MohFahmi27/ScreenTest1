package com.mfahmi.screentest1.data.dummy

object EventDummyData {
    fun getEventDataDummy(): ArrayList<Event> {
        return arrayListOf(
            Event("Happy New Year", "Dec 32 2021", "https://www.wikihow.com/images/thumb/d/df/Celebrate-the-New-Year-Step-1-Version-3.jpg/v4-460px-Celebrate-the-New-Year-Step-1-Version-3.jpg"),
            Event("Holi Event", "Oct 09 2021", "https://adbalon.com/blog/wp-content/uploads/2020/05/mch-group-live-marketing-aktivierung.jpg"),
            Event("Moto GP Event", "Jul 28 2021", "https://cdn-1.motorsport.com/images/amp/24v8Jwz6/s6/fabio-quartararo-yamaha-factor.jpg"),
            Event("Nascar Event", "Jan 26 2021", "https://nascar.nbcsports.com/wp-content/uploads/sites/9/2020/05/GettyImages-1211257471-e1589563289998.jpg")
        )
    }
}