--[[
  Generated by Scrooge
    version: ?
    rev: ?
    built at: ?
--]]

-- Import this file with:
--   require 'thrift.Request'
-- Note: This file depends on libthrift!



local Request = {
  ttype = 'struct',
  name = 'Request',
  fields = {
    [1] = { name = 'aList', required = true, ttype = 'list', value = 'string', },
    [2] = { name = 'aSet', required = true, ttype = 'set', value = 'i32', },
    [3] = { name = 'aMap', required = true, ttype = 'map', key = 'i64', value = 'i64', },
  }
}

local binaryCodec = require 'libthrift'

Request.__codec = binaryCodec.codec(Request)

function Request:read(bin)
  return self.__codec:read(bin)
end

function Request:readTensor(bin)
  return self.__codec:readTensor(bin)
end

function Request:write(params)
  return self.__codec:write(params)
end

function Request:writeTensor(params)
  return self.__codec:writeTensor(params)
end

return Request
