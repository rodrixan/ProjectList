package com.example.rodri.projectlist.common.fragment

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract fun getFragmentTag(): String
}