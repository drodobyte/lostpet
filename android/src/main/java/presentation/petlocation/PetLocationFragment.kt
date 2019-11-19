package presentation.petlocation

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import drodobytecom.lostpet.R

class PetLocationFragment : Fragment() {

    private var granted: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, saved: Bundle?
    ): View = inflater.inflate(R.layout.pet_location_fragment, container, false)

    override fun onViewCreated(view: View, saved: Bundle?) {
        super.onViewCreated(view, saved)
        requestLocationPermission()
        if (granted)
            moveCameraToCurrentLocation()
    }

    private fun moveCameraToCurrentLocation() {
        (childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment)
            .getMapAsync { map ->
                map.isMyLocationEnabled = true
                map.uiSettings.isMyLocationButtonEnabled = true
                LocationServices.getFusedLocationProviderClient(context!!).lastLocation
                    .addOnSuccessListener { loc ->
                        map.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                LatLng(loc.latitude, loc.longitude), 16f
                            )
                        )
                    }
            }
    }

    private fun requestLocationPermission() =
        if (checkSelfPermission(context!!, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED)
            granted = true
        else
            requestPermissions(activity!!, arrayOf(ACCESS_FINE_LOCATION), 0)

    override fun onRequestPermissionsResult(
        code: Int, permissions: Array<out String>, results: IntArray
    ) {
        if (code == 0 && results.isNotEmpty() && results[0] == PERMISSION_GRANTED) {
            granted = true
            moveCameraToCurrentLocation()
        }
    }
}