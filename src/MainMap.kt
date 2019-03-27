package com.example.ad.retrofittest.Maps

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.*
import android.widget.*

import com.example.ad.retrofittest.Account.Login
import com.example.ad.retrofittest.Account.Login.Companion.loginGlobal
import com.example.ad.retrofittest.Account.Login.Companion.tokenGlobal

import com.example.ad.retrofittest.Activities.AllActivityList
import com.example.ad.retrofittest.Chat.ChatList
import com.example.ad.retrofittest.Common_Clases.HomeWatcher
import com.example.ad.retrofittest.Common_Clases.OnHomePressedListener
import com.example.ad.retrofittest.Common_Clases.SharedPreferencesRestore
import com.example.ad.retrofittest.Common_Clases.TopPanels.TopPanelFragmentImage
import com.example.ad.retrofittest.Common_Clases.TopPanels.TopPanelFragmentSearch
import com.example.ad.retrofittest.Friends.Friends
import com.example.ad.retrofittest.Images.MainImage
import com.example.ad.retrofittest.Model.Event.EventEdit
import com.example.ad.retrofittest.Model.Event.EventFull
import com.example.ad.retrofittest.Model.Event.EventMin
import com.example.ad.retrofittest.Model.UserNewModel.User
import com.example.ad.retrofittest.Profile.Profile
import com.example.ad.retrofittest.R
import com.example.ad.retrofittest.Retrofit.ApiUtils
import com.example.ad.retrofittest.Retrofit.Services.NewServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import com.squareup.picasso.Picasso

import java.io.Serializable
import java.text.SimpleDateFormat

import de.hdodenhof.circleimageview.CircleImageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import com.example.ad.retrofittest.Common_Clases.ErrorHandler.CallbackWrapper
import com.example.ad.retrofittest.Common_Clases.HideKeyBoard

class MainMap : AppCompatActivity(), OnMapReadyCallback, Serializable, NavigationView.OnNavigationItemSelectedListener {
    private var mMap: GoogleMap? = null
    private var apiServiceStart: NewServices? = null
    private var locationManager: LocationManager? = null
    private var markerList: List<EventMin>? = null
    private var activity: EventFull? = null
    private var mClusterManager: ClusterManager<MyItem>? = null
    private var idOfImageMarker: Int = 0
    private var drawer: DrawerLayout? = null
    private var menuFriends: TextView? = null
    private var menuLikes: TextView? = null
    private var menuNick: TextView? = null
    private var profileImage: CircleImageView? = null
    private var clusterManagerFlag = false
    private var searchFragmentFlag = false
    private var imageFragmentFlag = false
    private var frameLayout: FrameLayout? = null
    private lateinit var view: View
    private var Details: EventFull? = null
    private lateinit var intentForMarkers: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        locationManager = baseContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        apiServiceStart = ApiUtils.getGeneralService(baseContext)
        val toolbar = findViewById<Toolbar>(R.id.toolbarNavigation)
        drawer = findViewById(R.id.activity_start_layout_id)
        frameLayout = findViewById(R.id.frame_map) as FrameLayout

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val headView = navigationView.getHeaderView(0)
        profileImage = headView.findViewById(R.id.imageNavigationMenuId)
        menuFriends = headView.findViewById(R.id.friendsNavigation)
        menuLikes = headView.findViewById(R.id.likesNavigation)
        menuNick = headView.findViewById(R.id.nickNavigation)
         view = (this
                .findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)

        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer!!.addDrawerListener(toggle)

        toggle.syncState()
        toggle.isDrawerIndicatorEnabled = false
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu)


        toolbar.setNavigationOnClickListener {
            if (drawer!!.isDrawerOpen(Gravity.END)) {
                drawer!!.closeDrawer(Gravity.END)
            } else {
                drawer!!.openDrawer(Gravity.END)
            }
        }

        frameLayout!!.setOnTouchListener { view, motionEvent ->
            HideKeyBoard.hideKeyboardFrom(baseContext, view)
            false
        }

        //checkHomeClicked()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // getIntent() should always return the most recent
        getIntent()
        setIntent(intent)

        aboutMe()
        startMap()
    }

    public override fun onResume() {
        super.onResume()
        val token = SharedPreferencesRestore.token_restore(baseContext)
        val login = SharedPreferencesRestore.login_restore(baseContext)
        val mainPhotoUrl = SharedPreferencesRestore.mainPhotoRestore(baseContext)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        apiServiceStart = ApiUtils.getGeneralService(baseContext)

        if (token != "" && login != "") {
            tokenGlobal = token
            loginGlobal = login
            imageUrlGlobal = mainPhotoUrl
        }

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setCheckedItem(R.id.MapIdMenuNavigation)

        aboutMe()
        startMap()
    }


    fun aboutMe() {
        Login.compositeDisposable.add(apiServiceStart!!.Iabout("https://api.chillmeets.pl/v1/me")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : CallbackWrapper<User>(baseContext) {
                    override fun onNext(response: User) {
                            imageUrlGlobal = response.mainPhotoUrl
                            Login.currentUserId = response.id
                            val nick = response.firstName
                            SharedPreferencesRestore.mainPhotoSave(imageUrlGlobal, this@MainMap)
                            topPanelImageFragment()
                            menuNick!!.text = nick

                            Picasso.with(baseContext)
                                    .load(imageUrlGlobal)
                                    .fit()
                                    .into(profileImage)
                    }
                }))
    }


    fun startMap() {
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as MapFragment
        mapFragment.getMapAsync(this)
    }

    fun addActivity(view: View) {
        val dialog = AddActivityFragmentMap()
        dialog.show(supportFragmentManager, "AddActivityFragmentMap")
    }

    fun SearchActivity(view: View) {
        topPanelSearchFragment()
    }

    fun getLocalization(){
        if (!(ActivityCompat.checkSelfPermission(applicationContext, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        || ActivityCompat.checkSelfPermission(applicationContext, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED))
            mMap!!.isMyLocationEnabled = true
        try {
            val locationGPS: Location = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            //here what you need:
            val latitude = locationGPS.latitude
            val longitude = locationGPS.longitude
            //create marker
            val myGPSPosition = LatLng(latitude, longitude)
            mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(myGPSPosition, 13f))

        } catch (e: NullPointerException) {
            e.printStackTrace()
            Toast.makeText(this, "Unable to get GPS data", Toast.LENGTH_SHORT).show()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            Toast.makeText(this, "Unable to get GPS data", Toast.LENGTH_SHORT).show()
        }
    }

    fun onClusterClicked(){
        mClusterManager!!.setOnClusterClickListener { cluster ->
            mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    cluster.position, Math.floor((mMap!!
                    .cameraPosition.zoom + 1).toDouble()).toFloat()), 300, null)
            true
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (clusterManagerFlag == false) {
            mClusterManager = ClusterManager(this, mMap)
            clusterManagerFlag = true
        }

        mMap!!.setOnCameraIdleListener(mClusterManager)
        mMap!!.setOnMarkerClickListener(mClusterManager)
        mMap!!.setOnMapClickListener { LatLng ->
            if(searchFragmentFlag) topPanelImageFragment()
            HideKeyBoard.hideKeyboardFrom(applicationContext, view)
        }

        getLocalization()
        onClusterClicked()
        FromActivitiesHandle()
    }

    fun FromActivitiesHandle(){
        intentForMarkers=intent

        val fragmentAddActivityFlag = intentForMarkers.getIntExtra("fragmentAddActivityFlag", 0)
        if (fragmentAddActivityFlag == 1)  addActivityFrom()

        val editActivityFlag = intentForMarkers.getIntExtra("EditActivityFlag", 0)
        if (editActivityFlag == 1)  editActivityFrom()

        val filtrMarkerListFlag = intentForMarkers.getIntExtra("filtrMarkerListFlag", 0)
        if (filtrMarkerListFlag == 1)  filtrActivityFrom()

        else {
            try {
                mClusterManager!!.clearItems()
                getAllMarkers()
            } catch (e: Exception) {
                Toast.makeText(baseContext, "Odśwież mapę(getAll)", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun addActivityFrom(){
        val kind = intentForMarkers.extras!!.getString("kindOfActivity")
        val description = intentForMarkers.extras!!.getString("descriptionOfActivity")
        val date = intentForMarkers.extras!!.getString("startDate")
        val address = intentForMarkers.extras!!.getString("address")
        val lat = intentForMarkers.extras!!.getDouble("lat", 0.0)
        val lng = intentForMarkers.extras!!.getDouble("lng", 0.0)

        try {
            val activity = EventEdit()
                    .setEventKindName(kind)
                    .setEventAccess("OPEN")
                    .setName("default")
                    .setDescription(description)
                    .setAddress(address)
                    .setLng(lng)
                    .setLat(lat)
                    .setStartDate(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(date)) as EventEdit

            sendMarkerCall(activity, true)

        } catch (e: Exception) {
            Log.i("errorAdd", e.toString())
        }
    }

    fun editActivityFrom(){
        val lat = intentForMarkers.extras!!.getDouble("EditActivityLat", 0.0)
        val lng = intentForMarkers.extras!!.getDouble("EditActivityLng", 0.0)
        val latLng = LatLng(lat, lng)
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
    }

    fun filtrActivityFrom(){
        mClusterManager!!.clearItems()
        for (i in TopPanelFragmentSearch.FiltrList.indices) {
            val activityIdFiltredMarkers = TopPanelFragmentSearch.FiltrList[i].id
            val kindOfActivity = TopPanelFragmentSearch.FiltrList[i].eventKind.label
            val lat = TopPanelFragmentSearch.FiltrList[i].lat
            val lng = TopPanelFragmentSearch.FiltrList[i].lng

            try {
                setMarker(kindOfActivity, lat, lng, activityIdFiltredMarkers, false)
            } catch (e: Exception) {
                Log.i("errorFiltr", e.toString())
            }

        }
    }

    fun sendMarkerCall(body: EventEdit, zoom: Boolean) {
        Login.compositeDisposable
                .add(apiServiceStart!!.ImarkerSend("https://api.chillmeets.pl/v1/events/add", body)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : CallbackWrapper<EventFull>(baseContext) {
                            override fun onNext(response: EventFull) {
                                activity = response

                                val activityIdSendMarkerCall = activity!!.idTemporary
                                val lat = activity!!.lat
                                val lng = activity!!.lng
                                val type = activity!!.eventKindName

                                try {
                                    setMarker(type, lat, lng, activityIdSendMarkerCall, true)
                                } catch (e: Exception) {
                                    Log.i("errorSend", e.toString())
                                }
                            }}))
    }

    fun setMarker(kind: String, lat: Double?, lng: Double?, markerId: Int, zoom: Boolean)  {
        val latLng = LatLng(lat!!, lng!!)
        idOfImageMarker = getDrawableId(kind)
        val offsetItem = MyItem(latLng, markerId,
                bitmapDescriptorFromVector(baseContext, idOfImageMarker))

        mClusterManager!!.addItem(offsetItem)

        val renderer = CustomClusterRenderer(baseContext, mMap, mClusterManager)
        mClusterManager!!.renderer = renderer

        if (zoom)
            mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))

        mClusterManager!!.setOnClusterItemClickListener  { clusterItem ->
            val i = clusterItem.idOfMarker
            getActivityDetails(i)
            true
        }
    }


    fun getActivityDetails(activity_id: Int)  {
        val id = Integer.toString(activity_id)
        val url_address = "https://api.chillmeets.pl/v1/events/$id"
        Login.compositeDisposable.add(apiServiceStart!!.igetActivityDetails(url_address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : CallbackWrapper<EventFull>(baseContext) {

                    override fun onNext(response: EventFull) {
                        Log.i("asderasdwer2", "przeszlo2")
                        Details = response
                        val hourOnly = SimpleDateFormat("HH:mm")
                        val dateOnly = SimpleDateFormat("yyyy-MM-dd")
                        val nick = Details!!.creator.login
                        val creatorId = Details!!.creator.id //TODO: nie zwraca mi id creatora
                        val time = hourOnly.format(Details!!.startDate)
                        val kind = Details!!.eventKindName
                        val adress = Details!!.address
                        val date= dateOnly.format(Details!!.startDate)
                        val descritption = Details!!.description
                        val image_url = Details!!.creator.mainPhoto.photoUrl

                        val dialog = DialogFragmentJoin()
                        dialog.show(supportFragmentManager, "DialogFragmentJoin")

                        //PACK DATA IN A BUNDLE
                        val bundle =  Bundle()
                        bundle.putInt("ActivityId", activity_id)
                        bundle.putInt("creatorId", creatorId)
                        bundle.putString("adress", adress)
                        bundle.putString("nick", nick)
                        bundle.putString("time", time)
                        bundle.putString("kind", kind)
                        bundle.putString("date", date)
                        bundle.putString("descritption", descritption)
                        bundle.putString("image_url", image_url)

                        //PASS OVER THE BUNDLE TO OUR FRAGMENT
                        dialog.arguments = bundle
                    }}))
    }

    fun getAllMarkers() {
        Login.compositeDisposable.add(apiServiceStart!!
                .IgetAllActivities("https://api.chillmeets.pl/v1/markers/all")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : CallbackWrapper<List<EventMin>>(baseContext) {
                    override fun onNext(response: List<EventMin>) {
                        markerList = response
                       // boostMarkers(markerList!!)
                        for (i in markerList!!.indices) {
                            val activityIdAllMarkers = markerList!![i].id
                            val kindOfActivity = markerList!![i].eventKindName
                            val lat = markerList!![i].lat
                            val lng = markerList!![i].lng

                            try {
                                setMarker(kindOfActivity, lat, lng, activityIdAllMarkers, false)
                            } catch (e: Exception) {
                                Log.i("errorGetAll", e.toString())
                            }
                        }
                    }
                }))
    }



    fun topPanelImageFragment() {

        searchFragmentFlag = false
        imageFragmentFlag = true

        val myFragment = TopPanelFragmentImage()
        val manager = fragmentManager.beginTransaction()

        manager.replace(R.id.frame_map, myFragment)
        manager.commit()
    }

    fun topPanelSearchFragment() {

        searchFragmentFlag = true
        imageFragmentFlag = false

        val myFragment = TopPanelFragmentSearch()
        val manager = fragmentManager.beginTransaction()

        manager.replace(R.id.frame_map, myFragment)
        manager.commit()


    }

    fun checkHomeClicked() {
        val mHomeWatcher = HomeWatcher(this)
        mHomeWatcher.setOnHomePressedListener(object : OnHomePressedListener {
            override fun onHomePressed() {

            }

            override fun onHomeLongPressed() {}
        })
        mHomeWatcher.startWatch()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_navigation_drawer_menu_drawer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item != null && item.itemId == android.R.id.home) {
            if (drawer!!.isDrawerOpen(Gravity.END)) {
                drawer!!.closeDrawer(Gravity.END)
            } else {
                drawer!!.openDrawer(Gravity.END)
            }
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        val context = baseContext
        val i: Intent
        when (item.itemId) {
            R.id.MapIdMenuNavigation -> {
                i = Intent(context, MainMap::class.java)
                startActivity(i)
            }



            R.id.profileIdMenuNavigation -> {
                i = Intent(context, Profile::class.java)
                i.putExtra("idProfil", Login.currentUserId)
                startActivity(i)
            }

            R.id.photosIdMenuNavigation -> {
                i = Intent(context, MainImage::class.java)
                i.putExtra("userIdPhotos", Login.currentUserId)
                startActivity(i)
            }

            R.id.friendsIdMenuNavigation -> {
                i = Intent(context, Friends::class.java)
                i.putExtra("userIdFriends", Login.currentUserId)
                startActivity(i)
            }

            R.id.messagesIdMenuNavigation -> {
                i = Intent(context, ChatList::class.java)
                startActivity(i)
            }

            R.id.activitiesIdMenuNavigation -> {
                i = Intent(context, AllActivityList::class.java)
                startActivity(i)
            }


            R.id.LogoutIdMenuNavigation -> {
                context.getSharedPreferences("chillmeets_auth_v1", 0).edit().clear().commit()
                tokenGlobal = "resetToken"
                i = Intent(context, Login::class.java)
                startActivity(i)
                finish()
            }
        }

        drawer!!.closeDrawer(Gravity.END)
        return true
    }

    companion object {
        //need this to get permission from user
        val ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
        val ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
        @JvmStatic var imageUrlGlobal: String = "http://cdn.onlinewebfonts.com/svg/img_173956.png"



        fun getDrawableId(name: String): Int {
            try {
                val fld = R.drawable::class.java.getField(name)
                return fld.getInt(null)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return -1
        }

        fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor {
            val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
            vectorDrawable.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
            val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            vectorDrawable.draw(canvas)
            return BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
}